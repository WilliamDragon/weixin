package com.gjl.weixin.smt;

import java.util.concurrent.*;

public class testClerk {

    public static void main(String[] args) {
       /* Clerk clerk = new Clerk();
        Consumer consumer = new Consumer(clerk);
        Producer producer = new Producer(clerk);
        Thread c1 = new Thread(consumer);
        Thread p1 = new Thread(producer);
        Thread p2 = new Thread(producer);
        Thread p3 = new Thread(producer);
        c1.setName("消费者1");
        p1.setName("生产者1");
        p2.setName("生产者2");
        p3.setName("生产者3");*/

        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        new ThreadPoolExecutor(10,20,10L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

    }
}
