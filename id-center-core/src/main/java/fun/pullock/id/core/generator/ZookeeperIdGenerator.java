package fun.pullock.id.core.generator;

import fun.pullock.id.core.zookeeper.CuratorClient;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "id-generator.type", havingValue = "zookeeper")
@Component
public class ZookeeperIdGenerator implements IdGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperIdGenerator.class);

    private static final String ID_PATH = "/id/ID-";

    @Resource
    private CuratorClient curatorClient;

    @Override
    public Long generate() {
        String path = curatorClient.createEphemeralSequentialPath(ID_PATH);
        if (path == null || path.isEmpty()) {
            LOGGER.warn("Can not generate id from zookeeper, fallback to simple id generator");
            return -1L;
        }
        return Long.valueOf(path.substring(ID_PATH.length()));
    }
}
