package com.jensuper.springboot.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: ExecutorConfig
 * @Description: 线程配置类
 * @author:jichao
 * @date: 2019/6/11
 * @Copyright: 2019/6/11 www.rongdasoft.com
 * Inc. All rights reserved.
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 实例化线程池
     * @return
     */
    @Bean
    public Executor executorInstance() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(5);
        //最大线程数
        executor.setMaxPoolSize(5);
        //设置队列配置大小
        executor.setQueueCapacity(99999);
        //设置线程前缀
        executor.setThreadNamePrefix("async-thread-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //初始化
        executor.initialize();
        return executor;
    }
}
