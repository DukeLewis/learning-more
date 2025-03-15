package learning.more.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@Configuration
public class ThreadPoolConfig {
    /**
     * 缓存线程池
     */
    @Bean(name = "cachedThreadPool")
    public ExecutorService cachedThreadPool() {
        return Executors.newCachedThreadPool();
    }
}
