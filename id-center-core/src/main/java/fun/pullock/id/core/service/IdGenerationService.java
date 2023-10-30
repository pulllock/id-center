package fun.pullock.id.core.service;


import fun.pullock.id.core.generator.IdGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IdGenerationService {

    @Resource
    private IdGenerator idGenerator;


    public String generateOne() {
        return idGenerator.generate();
    }
}
