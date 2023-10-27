package fun.pullock.id.core.generator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "id-generator.type", havingValue = "redis")
@Component
public class RedisIdGenerator implements IdGenerator {

    @Override
    public Long generate() {
        return 000000L;
    }
}
