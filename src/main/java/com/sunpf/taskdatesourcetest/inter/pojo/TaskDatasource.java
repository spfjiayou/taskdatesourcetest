package com.sunpf.taskdatesourcetest.inter.pojo;

public class TaskDatasource {

    private String task_id;
    private String task_name;
    private String isruning;
    private String UUID;
    private String status;
    private String cron;
    private String classpath;


    private String ex_method;
    private String current_thead;

    public String getCurrent_thead() {
        return current_thead;
    }

    public void setCurrent_thead(String current_thead) {
        this.current_thead = current_thead;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setIsruning(String isruning) {
        this.isruning = isruning;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public void setEx_method(String ex_method) {
        this.ex_method = ex_method;
    }

    public String getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getIsruning() {
        return isruning;
    }

    public String getUUID() {
        return UUID;
    }

    public String getStatus() {
        return status;
    }

    public String getCron() {
        return cron;
    }

    public String getClasspath() {
        return classpath;
    }

    public String getEx_method() {
        return ex_method;
    }

}
