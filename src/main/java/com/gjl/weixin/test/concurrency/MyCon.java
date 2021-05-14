package com.gjl.weixin.test.concurrency;

import com.gjl.weixin.test.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author WilliamDragon
 * @Date 2021/4/25 15:20
 * @Version 1.0
 */
public class MyCon {

    /*@Bean
    public TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutorss = new ThreadPoolTaskExecutor();
        taskExecutorss.setMaxPoolSize(10);
        taskExecutorss.setCorePoolSize(40);
        taskExecutorss.setKeepAliveSeconds(5);
        taskExecutorss.setQueueCapacity(40);
        return taskExecutorss;
    }*/
    public static void main(String[] args) {
        MyThread1 myThread = new MyThread1();
        new Thread(myThread).start();
    }

    public static class MyThread1 implements Runnable{
        @Override
        public void run() {
            System.out.println("多线程启动");
        }
    }
}
