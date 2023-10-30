package fun.pullock.id.core.config;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnExpression("'${id-generator.type}'.startsWith('zookeeper-')")
@Configuration
public class CuratorConfig {

    @Resource
    private CuratorProperties curatorProperties;

    @Bean
    public CuratorFramework curatorFramework() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(curatorProperties.getUrl())
                .namespace(curatorProperties.getNamespace())
                .sessionTimeoutMs(curatorProperties.getSessionTimeout())
                .connectionTimeoutMs(curatorProperties.getConnectionTimeout())
                .retryPolicy(new RetryNTimes(curatorProperties.getRetryTimes(), curatorProperties.getElapsedTime()))
                .build();
        client.start();
        return client;
    }
}
