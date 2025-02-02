package com.itmo.notifier.configuration;

import com.itmo.notifier.client.DwhRetryer;
import com.itmo.notifier.exception.DwhErrorDecoder;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
public class NotifierConfiguration {
    @Bean("notifier-executor")
    public Executor orderExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("NOTIFIER-EXECUTOR-");
        executor.initialize();
        return executor;
    }
    @Bean
    public DwhErrorDecoder dwhErrorDecoder(){
        return new DwhErrorDecoder();
    }

    public class FeignClientConfig{
        @Bean
        public Retryer retryer(){
            return new DwhRetryer(3,10);
        }
    }

}
