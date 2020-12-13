package com.sunpf.taskdatesourcetest.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    private final static Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    @Autowired
    public  void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }




    //获取applicationcontext
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    //通过name 获取对应的bean
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    //通过class获取bean
    public static <T>T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
    //通过name，以及clazz返回指定的bean
    public static <T>T getBean(String name , Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }



}
