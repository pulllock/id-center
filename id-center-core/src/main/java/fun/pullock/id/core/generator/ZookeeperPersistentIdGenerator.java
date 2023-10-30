package fun.pullock.id.core.generator;

import fun.pullock.id.core.zookeeper.CuratorClient;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "id-generator.type", havingValue = "zookeeper-persistent")
@Component
public class ZookeeperPersistentIdGenerator implements IdGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperPersistentIdGenerator.class);

    private static final String ID_PATH = "/id/ID-";

    @Resource
    private CuratorClient curatorClient;

    @Override
    public String generate() {
        String path = curatorClient.createPersistentSequentialPath(ID_PATH);
        if (path == null || path.isEmpty()) {
            LOGGER.warn("Can not generate id from zookeeper, fallback to simple id generator");
            return "NULL";
        }
        return path.substring(ID_PATH.length());
    }
}
