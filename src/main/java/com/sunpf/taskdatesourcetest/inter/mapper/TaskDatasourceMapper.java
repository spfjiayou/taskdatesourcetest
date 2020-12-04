package com.sunpf.taskdatesourcetest.inter.mapper;

import com.sunpf.taskdatesourcetest.inter.pojo.TaskDatasource;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskDatasourceMapper {

    @Results({
            @Result(property = "task_id",column = "task_id"),
            @Result(property = "task_name",column = "task_name"),
            @Result(property = "isruning",column = "isruning"),
            @Result(property = "UUID",column = "UUID"),
            @Result(property = "status",column = "status"),
            @Result(property = "cron",column = "cron"),
            @Result(property = "classpath",column = "classpath"),
            @Result(property = "ex_method",column = "ex_method"),
            @Result(property = "current_thead",column = "current_thead")
    })


    @Select("select * from task_datasource")
    List<TaskDatasource> selectTaskDatesource();


    @Select("select * from task_datasource where task_id=#{task_id}")
    TaskDatasource selectTaskDatasourceById(String task_id);


    @Update("update task_datasource set isruning=#{newTaskDatasource.isruning},UUID=#{newTaskDatasource.UUID} where task_id=#{oldTaskDatasource.task_id} and isruning=#{oldTaskDatasource.isruning} and UUID=#{oldTaskDatasource.UUID}")
    Integer updateTaskDatasourceIsRuning(TaskDatasource newTaskDatasource,TaskDatasource oldTaskDatasource);















}
