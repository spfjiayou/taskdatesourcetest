package com.sunpf.taskdatesourcetest.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskRunner implements ApplicationRunner {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    //注入定时任务执行
    @Autowired
    private ScheduleTask scheduleTask;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("开始启动定时任务。。。。。");
        scheduleTask.startTask();
        logger.info("定时任务启动完毕。。。。");

    }
}
