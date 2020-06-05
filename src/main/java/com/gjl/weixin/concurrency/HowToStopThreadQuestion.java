package com.gjl.weixin.concurrency;

public class HowToStopThreadQuestion {

    public static void main(String[] args) throws InterruptedException {

        Action action = new Action();
        Thread t1 = new Thread(action, "t1");

        t1.start();
        action.setStopped(true);
        t1.join();

       Thread t2 = new Thread(()->{
           if(!Thread.currentThread().isInterrupted()){
               action();
           }
       },"t2");
       t2.start();
       t2.interrupt();
       t2.join();
    }

    private static class Action implements Runnable{

        private volatile boolean stopped = false;
        @Override
        public void run() {
            if(!stopped){
                action();
            }
        }

        public void setStopped(boolean stopped) {
            this.stopped = stopped;
        }
    }

    private static void action(){
        System.out.printf("线程[%s] 正在执行...\n",Thread.currentThread().getName());
    }

   /* private static class Action2 implements Runnable{

        public void run(){
            if()
            action();
        }
    }*/
}
