package fun.pullock.id.core.zookeeper;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
}