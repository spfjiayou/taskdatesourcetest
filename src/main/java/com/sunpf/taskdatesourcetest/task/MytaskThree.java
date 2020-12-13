package com.sunpf.taskdatesourcetest.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 验证定时任务
 */
@Component
public class MytaskThree {

    private final static Logger logger = LoggerFactory.getLogger(MytaskThree.class);

    public void dotask() throws InterruptedException {
        logger.info("定时任务"+this.getClass().getName()+"开始执行.......");
        Thread.sleep(5000);
        logger.info("定时任务"+this.getClass().getName()+"执行完毕Sun.......");

    }




}
