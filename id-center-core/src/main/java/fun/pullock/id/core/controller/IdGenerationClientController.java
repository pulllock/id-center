package fun.pullock.id.core.controller;

import fun.pullock.general.model.Result;
import fun.pullock.id.api.service.IdGenerationClient;
import fun.pullock.id.core.service.IdGenerationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdGenerationClientController implements IdGenerationClient {

    @Resource
    private IdGenerationService idGenerationService;

    @Override
    public Result<String> generateOne() {
        return new Result<>(idGenerationService.generateOne());
    }
}
