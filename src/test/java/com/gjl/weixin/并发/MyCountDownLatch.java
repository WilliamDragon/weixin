package com.gjl.weixin.并发;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class MyCountDownLatch {

    //有10个人赛跑
    public static void testCountDownLatch(){
        int threadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0; i<10; i++){
             new Thread(()->{
                 System.out.println("线程"+Thread.currentThread().getName()+"已经出发");
                 try{
                     Thread.sleep(3000);
                 }catch(InterruptedException e){
                     e.printStackTrace();
                 }
                 System.out.println("线程"+Thread.currentThread().getName()+"到达终点");
                 countDownLatch.countDown();//计算器 减一
             }).start();
        }
        try{
            countDownLatch.await();//当前线程等待计数器为0
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("10个线程已经执行完毕！开始计算排名");
    }
    //抓毒犯
    public static void DrugDealer(){

        CountDownLatch start = new CountDownLatch(1);//开始计数器
        CountDownLatch end = new CountDownLatch(10);//结束计数器

       // LinkedBlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>(10);

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=0; i<10; i++){
            final String cirmal = "第"+i+"个毒贩";
            executorService.submit(()->{
                try{
                    start.await();
                    Thread.sleep(3000);
                    System.out.println(cirmal+"被逮捕啦");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //抓掉一个毒贩，end计数器减一
                    end.countDown();
                }

            });
        }
        start.countDown();//抓毒犯正式开始
        try{
            end.await(); //等待毒贩全部抓捕完毕
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("毒贩已经抓捕，收工啦");
        executorService.shutdown(); //收工
    }


    public static void main(String[] args) {
        //MyCountDownLatch.testCountDownLatch();//10个人跑步
        MyCountDownLatch.DrugDealer();//抓10个毒贩
    }
}
