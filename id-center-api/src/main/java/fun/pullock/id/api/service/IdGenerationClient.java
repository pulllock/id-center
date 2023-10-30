package fun.pullock.id.api.service;

import fun.pullock.general.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ID生成服务
 */
public interface IdGenerationClient {

    /**
     * 生成一个ID
     * @return 生成的ID
     */
    @RequestMapping(path = "/rpc/id/generation/one")
    Result<String> generateOne();
}
