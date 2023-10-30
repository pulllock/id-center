package fun.pullock.id.core.service;


import fun.pullock.id.core.generator.IdGenerator;
import jakarta.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class IdGenerationService implements ApplicationContextAware {

    @Resource
    private IdGenerator idGenerator;

    private ApplicationContext applicationContext;

    public String generateOne() {
        return idGenerator.generate();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
