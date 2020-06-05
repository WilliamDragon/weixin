package com.gjl.weixin.smt;

public class Clerk {
    int product; //产品数量

    //生产产品的方法
    public synchronized  void addProduct(){
        if(product>=20){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else{
            product++;
            System.out.println(Thread.currentThread().getName()+"生产了第"+product+"个产品");
            notifyAll();
        }
    }
    //消费产品的方法
    public synchronized void consumeProduct(){
        if(product<=0){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }else{
            product--;
            System.out.println(Thread.currentThread().getName()+"消费了第"+product+"个产品");
            notifyAll();
        }
    }
    //生产者生产产品线程
    class Producer implements Runnable{
        Clerk clerk;
        public Producer(Clerk clerk){
            this.clerk = clerk;
        }
        @Override
        public void run() {
            System.out.println("生产这生产了第"+product+"个产品");

            while(true){
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clerk.addProduct();
            }
        }
    }

    class Consumer implements Runnable{

        Clerk clerk;
        public Consumer(Clerk clerk){
            this.clerk = clerk;
        }
        @Override
        public void run() {
            System.out.println("消费了第"+product+"个产品");
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }


}

