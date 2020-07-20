package com.gjl.weixin.并发;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LIstAdd2 {

    private volatile static List list = new ArrayList<String>();

    public void add(){
        list.add("test..");
    }
    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        final Object lock = new Object();
        final LIstAdd2 list2 = new LIstAdd2();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(()->{
            try{
                synchronized (lock){
                    System.out.println("线程t1已经启动");
                    for(int i=0;i<10;i++){
                        list2.add();
                        System.out.println(Thread.currentThread().getName()+"新增了一个元素");
                        Thread.sleep(500);
                        if(list2.getSize() == 5){
                            System.out.println("已经发出通知");
                            Thread.sleep(5000);
                            lock.notify();
                            //countDownLatch.countDown();
                        }
                    }
                 }
            }catch (InterruptedException  e){
                e.printStackTrace();
            }
        },"t1");
        Thread t2 = new Thread(()->{
                synchronized (lock){
                    System.out.println("线程t2已经启动");
                    if(list2.getSize() != 5){
                        try{
                            System.out.println("size() != 5,t2 wait释放锁！..");
                            lock.wait();
                            //countDownLatch.await();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                }
        },"t2");
        t2.start();
        t1.start();

    }

}
