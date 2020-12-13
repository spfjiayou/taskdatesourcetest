package com.sunpf.taskdatesourcetest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduleConfiguration {

    //添加定时任务线程管理器
    @Bean
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

}
