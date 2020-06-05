package com.gjl.weixin.concurrency;

public class ThreadExcutionQuestion {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ThreadExcutionQuestion::action,"t1");
        Thread t2 = new Thread(ThreadExcutionQuestion::action,"t2");
        Thread t3 = new Thread(ThreadExcutionQuestion::action,"t3");

        t1.start();
        //t1.join();
        while(t1.isAlive()){

        }
        t2.start();
        //t2.join();
        while(t2.isAlive()){

        }
        t3.start();
        //t3.join();
        while(t3.isAlive()){

        }
    }

    private static void action(){
        System.out.printf("线程[%s] 正在执行...\n",Thread.currentThread().getName());
    }
}
