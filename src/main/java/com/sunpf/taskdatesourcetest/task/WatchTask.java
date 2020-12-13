package com.sunpf.taskdatesourcetest.task;


import com.sunpf.taskdatesourcetest.component.ScheduleTask;
import com.sunpf.taskdatesourcetest.inter.mapper.TaskDatasourceMapper;
import com.sunpf.taskdatesourcetest.inter.pojo.TaskDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class WatchTask {

    private final static Logger logger = LoggerFactory.getLogger(WatchTask.class);

    @Autowired
    private ScheduleTask scheduleTask;




    public void dotask(){
        logger.info("守护定时任务启动定时任务已经启动"+ LocalDateTime.now() );
        //获取所有的定时任务进行判断，判断其是需要添加或者删除
        scheduleTask.changeTask();
        logger.info("守护定时任务完成。。。。");

    }
}
