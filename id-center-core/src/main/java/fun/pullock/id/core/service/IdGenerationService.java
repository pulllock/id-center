package fun.pullock.id.core.service;


import fun.pullock.id.core.generator.factory.IdGeneratorFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IdGenerationService {

    @Resource
    private IdGeneratorFactory idGeneratorFactory;

    public Long generateOne() {
        return idGeneratorFactory.getIdGenerator().generate();
    }
}
