package com.sunpf.taskdatesourcetest.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "task.properties")
@Component
public class TaskProperties {

    private String taskname;
    private String cron;
    private String clazz;
    private String method;

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTaskname() {
        return taskname;
    }

    public String getCron() {
        return cron;
    }

    public String getClazz() {
        return clazz;
    }

    public String getMethod() {
        return method;
    }
}
