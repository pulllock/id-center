package fun.pullock.id.core.generator;

import fun.pullock.id.core.zookeeper.CuratorClient;
import jakarta.annotation.Resource;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "id-generator.type", havingValue = "zookeeper-custom")
@Component
public class ZookeeperCustomIdGenerator implements IdGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperCustomIdGenerator.class);

    private static final String ID_PATH = "/id/ID";

    private static final String ID_ONE = "1";

    private static final String LOCK_PATH = "/lock";

    @Resource
    private CuratorClient curatorClient;

    @Override
    public String generate() {
        // 加锁
        InterProcessLock lock = new InterProcessMutex(curatorClient.getCuratorFramework(), LOCK_PATH);
        try {
            lock.acquire();
            try {
                // 检查节点/id/ID是否存在
                boolean pathExists = curatorClient.checkExists(ID_PATH);

                // 节点不存在，需要创建一个持久化节点并初始化节点数据为1
                String id = ID_ONE;
                if (!pathExists) {
                    curatorClient.createPersistentPath(ID_PATH, id);
                } else {
                    // 节点存在，取节点中保存的数据
                    id = curatorClient.getData(ID_PATH);

                    // 将节点中保存的数据加1
                    long nextId = Long.parseLong(id) + 1;
                    id = String.valueOf(nextId);
                }

                // 将新的id数据保存到节点中
                curatorClient.setData(ID_PATH, id);
                return id;
            } finally {
                lock.release();
            }
        } catch (Exception e) {
            LOGGER.error("Can not generate id from zookeeper, cause: ", e);
            throw new RuntimeException(e);
        }
    }
}
