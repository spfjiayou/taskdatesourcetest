package com.sunpf.taskdatesourcetest.component;

import com.sunpf.taskdatesourcetest.inter.mapper.TaskDatasourceMapper;
import com.sunpf.taskdatesourcetest.inter.pojo.TaskDatasource;
import com.sunpf.taskdatesourcetest.properties.TaskProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduleTask  {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);



    //已添加的定时任务进行一下记录
    private static HashMap<String,ScheduledFuture> currentTaskmap = new HashMap<>();
    //已添加定时任务的执行cron
    private static HashMap<String,String> cronMap = new HashMap<>();


    @Autowired
    private TaskDatasourceMapper taskDatasourceMapper;

    @Autowired
    private TaskProperties taskProperties;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public void startTask() {
        //获得定时任务
        List<TaskDatasource> allTaskDatasources = getAllTaskDatasource();
        //处理需要添加的定时任务
        allTaskDatasources =  checkTaskKist(allTaskDatasources);
        logger.info("开始添加定时任务....");
        for (int i = 0; i < allTaskDatasources.size(); i++) {
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(getRunnable(allTaskDatasources.get(i)), getTrigger(allTaskDatasources.get(i)));
            logger.info("定时任务" + allTaskDatasources.get(i).getTask_name()+"添加成功,定时任务执行时间为"+allTaskDatasources.get(i).getCron());
            currentTaskmap.put(allTaskDatasources.get(i).getTask_name(),future);
        }
        //添加定时扫描定时任务功能，通过application文件拿到基础扫描文件的信息
        logger.info("开始添加定时任务守护任务。。。");
        threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Object obj = SpringUtils.getBean(taskProperties.getTaskname());
                    Method method = obj.getClass().getMethod(taskProperties.getMethod(), null);
                    method.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(taskProperties.getCron());
                Date nextdate = trigger.nextExecutionTime(triggerContext);
                return nextdate;            }
        });

        logger.info("守护定时任务添加成功");





    }


    //添加定时任务执行信息
    private Runnable getRunnable(TaskDatasource taskDatasource) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Object obj = SpringUtils.getBean(taskDatasource.getTask_name());
                    Method method = obj.getClass().getMethod(taskDatasource.getEx_method(), null);
                    method.invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    logger.error("定时任务执行异常。。。" + e.getMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    //添加触发器
    private Trigger getTrigger(TaskDatasource taskDatasource) {
        //添加现有触发器规则
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(taskDatasource.getCron());
                Date nextdate = trigger.nextExecutionTime(triggerContext);
                cronMap.put(taskDatasource.getTask_name(),taskDatasource.getCron());
                return nextdate;

            }
        };
    }

    //判定数据库中的定时任务是否需要添加到定时任务中
    private List<TaskDatasource> checkTaskKist(List<TaskDatasource> allTaskDatasources) {
        //对状态为0未启动的定时任务进行剔除
        List<TaskDatasource> taskDatasources = new ArrayList<>();
        for (TaskDatasource taskDatasource : allTaskDatasources ) {
            if (taskDatasource.getStatus().equals("1"))
                taskDatasources.add(taskDatasource);
        }
        return taskDatasources;
    }


    //获取所有的定时任务
    private List<TaskDatasource> getAllTaskDatasource() {
        return taskDatasourceMapper.selectTaskDatesource();
    }


    //添加及删除定时任务
    public void changeTask(){
        //获取所有定时任务进行处理
        List<TaskDatasource> taskDatasources = taskDatasourceMapper.selectTaskDatesource();
        //删除现有定时任务
        for (TaskDatasource taskDatasource:taskDatasources){
            //判断现有的定时任务与数据库中的变化
            //先判断其状态后判断其定时任务时间是否发生变化
            if (taskDatasource.getStatus().equals("0")&&currentTaskmap.get(taskDatasource.getTask_name())!=null){
                delTask(taskDatasource);
            }else if (taskDatasource.getStatus().equals("1")&&!taskDatasource.getCron().equals(cronMap.get(taskDatasource.getTask_name()))){
                delTask(taskDatasource);
                addTask(taskDatasource);
            }
        }

    }

    //删除定时任务
    public void delTask(TaskDatasource taskDatasource){
        //获取所有删除的定时任务
        logger.info("开始删除定时任务。。。。");
        ScheduledFuture scheduledFuture = currentTaskmap.get(taskDatasource.getTask_name());
        if (scheduledFuture!=null){
            scheduledFuture.cancel(true);
            currentTaskmap.remove(taskDatasource.getTask_name());
            cronMap.remove(taskDatasource.getTask_name());
            logger.info("定时任务"+taskDatasource.getTask_name()+"已经删除");
        }

    }


    //添加定时任务操作
    public void addTask(TaskDatasource taskDatasource){
        //修改及添加定时任务
        ScheduledFuture scheduledFuture = currentTaskmap.get(taskDatasource.getTask_name());
        if (scheduledFuture!=null)
            scheduledFuture.cancel(true);
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(getRunnable(taskDatasource), getTrigger(taskDatasource));
        currentTaskmap.put(taskDatasource.getTask_name(),schedule);
        logger.info("定时任务"+taskDatasource.getTask_name()+"添加成功");
    }


}
