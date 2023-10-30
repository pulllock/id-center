package fun.pullock.id.core.zookeeper;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import static java.nio.charset.StandardCharsets.UTF_8;

@ConditionalOnBean(CuratorFramework.class)
@Component
public class CuratorClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorClient.class);

    @Resource
    private CuratorFramework curatorFramework;

    /**
     * 创建有序的临时节点
     * @param path 节点路径
     * @return
     */
    public String createEphemeralSequentialPath(String path) {
        try {
            return curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(path);
        } catch (Exception e) {
            LOGGER.error("Create ephemeral sequential path error, cause: ", e);
            return null;
        }
    }

    /**
     * 创建有序的持久化节点
     * @param path 节点路径
     * @return
     */
    public String createPersistentSequentialPath(String path) {
        try {
            return curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(path);
        } catch (Exception e) {
            LOGGER.error("Create persistent sequential path error, cause: ", e);
            return null;
        }
    }

    /**
     * 创建持久化节点
     * @param path 节点路径
     * @return
     */
    public String createPersistentPath(String path) {
        try {
            return curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path);
        } catch (Exception e) {
            LOGGER.error("Create persistent path error, cause: ", e);
            return null;
        }
    }

    /**
     * 创建持久化节点
     * @param path 节点路径
     * @param data 节点数据
     * @return
     */
    public String createPersistentPath(String path, String data) {
        try {
            if (data == null || data.isEmpty()) {
                return createPersistentPath(path);
            }

            return curatorFramework.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path, data.getBytes(UTF_8));
        } catch (Exception e) {
            LOGGER.error("Create persistent path error, cause: ", e);
            return null;
        }
    }

    /**
     * 检查节点是否存在
     * @param path 节点路径
     * @return
     */
    public boolean checkExists(String path) {
        try {
            return curatorFramework.checkExists().forPath(path) != null;
        } catch (Exception e) {
            LOGGER.error("Check path exists error, cause: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 从指定节点获取数据
     * @param path 节点路径
     * @return
     */
    public String getData(String path) {
        try {
            return new String(curatorFramework.getData().forPath(path));
        } catch (Exception e) {
            LOGGER.error("Get data error, path: {}, cause: ", path, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存数据到指定节点中
     * @param path 节点路径
     * @param data 数据
     * @return
     */
    public boolean setData(String path, String data) {
        try {
            return curatorFramework.setData().forPath(path, data.getBytes(UTF_8)) != null;
        } catch (Exception e) {
            LOGGER.error("Set data error, path: {}, data: {}, cause: ", path, data, e);
            throw new RuntimeException(e);
        }
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }
}
