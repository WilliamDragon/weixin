package com.gjl.weixin.test.concurrency.xpad;

import com.gjl.weixin.test.concurrency.TestModel;

/**
 * @Author WilliamDragon
 * @Date 2021/4/26 15:35
 * @Version 1.0
 */

public class SyncRunnbSingleton implements Runnable{
    private TestModel testModel;

    public SyncRunnbSingleton(TestModel testModel){
        this.testModel = testModel;
    }
    @Override
    public void run() {
        doTransaction(testModel);
    }

    public void doTransaction(TestModel model){

        //执行时间越多，多线程执行时间越长
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e1) {
        }
        System.out.println("==CoreSize:" + model.getCoreSize()+ "  ==QueueCapacity:" + model.getQueueCapacity());
    }

}
