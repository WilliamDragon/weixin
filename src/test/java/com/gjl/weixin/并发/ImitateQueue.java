package com.gjl.weixin.并发;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ImitateQueue {

    //承载元素的集合
    final private LinkedList list = new LinkedList<Object>();
    // 计数器进行计数
    private final AtomicInteger count = new AtomicInteger(0);
    //设置上下限
    private static  int maxSize;
    private static final int minSize=0;
    public ImitateQueue(int maxSize){
        this.maxSize = maxSize;
    }
    private final Object lock = new Object();
    public void put(Object object){
        synchronized (lock){
            if(count.get() == maxSize){
                try{
                    System.out.println("队列达到最大值，等待放入");
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
            list.add(object);
            count.getAndIncrement();
            System.out.println(" 元素 " + object + " 被添加 ");
            //唤醒其他线程取数
            lock.notify();
        }
    }

    public Object take(){
        Object temp = null;
        synchronized (lock){
            if(count.get() == minSize){
                try{
                    System.out.println("队列达到最小值，等待生产");
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            // 计数器递减
            count.getAndDecrement();
            // 取出元素
            temp = list.removeFirst();
            System.out.println(" 元素 " + temp + " 被消费 ");
            //唤醒其他线程取数
            lock.notify();
        }
        return temp;
    }

    public static void main(String[] args) throws Exception{

        final ImitateQueue m = new ImitateQueue(5);
        m.put("a");
        m.put("b");
        m.put("c");
        m.put("d");
        m.put("e");

        Thread t1 = new Thread(()->{
            m.put("N");
            m.put("M");
        },"t1");
        t1.start();
        Thread t2 = new Thread(()->{
            Object o1 = m.take();
            System.out.println("被取走的元素为"+o1);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            Object o2 = m.take();
            System.out.println("被取走的元素为"+o2);

        },"t2");
        // 休眠2秒钟
        TimeUnit.SECONDS.sleep(2);
        t2.start();
    }

}
