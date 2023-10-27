package fun.pullock.id.core.generator.factory;

import fun.pullock.id.core.generator.IdGenerator;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultIdGeneratorFactory implements IdGeneratorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultIdGeneratorFactory.class);

    @Value("${id-generator.type:zookeeper}")
    private String idGeneratorType;

    @Resource
    private Map<String, IdGenerator> idGenerators;

    @Override
    public IdGenerator getIdGenerator() {
        IdGenerator idGenerator = idGenerators.get(String.format("%s%s", idGeneratorType, "IdGenerator"));
        if (idGenerator == null) {
            LOGGER.warn("No generator found, fallback to simple id generator");
        }

        return idGenerator;
    }
}
