package com.sunpf.taskdatesourcetest;

import com.sunpf.taskdatesourcetest.component.ScheduleTask;
import com.sunpf.taskdatesourcetest.component.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sunpf.taskdatesourcetest.inter.mapper")
public class TaskdatesourcetestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TaskdatesourcetestApplication.class, args);


    }

}
