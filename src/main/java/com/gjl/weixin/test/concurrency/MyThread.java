package com.gjl.weixin.test.concurrency;

/**
 * @Author WilliamDragon
 * @Date 2021/5/13 10:29
 * @Version 1.0
 */

public class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("多线程启动");
    }
}
