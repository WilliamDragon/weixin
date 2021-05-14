package com.gjl.weixin.test.concurrency.xpad;

import com.gjl.weixin.test.concurrency.TestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author WilliamDragon
 * @Date 2021/4/25 14:25
 * @Version 1.0
 */
@Service
public class TestSyncRunList {

    private static final Logger logger = LoggerFactory.getLogger(TestSyncRunList.class);
    @Autowired
    private TaskExecutor taskExecutor;

    private static int totalThreadNum = 50;

    public void sendOrderBatch(List<TestModel> ipdpOrders){

        final CountDownLatch countDownLatch = new CountDownLatch(totalThreadNum);
        SyncRunList<TestModel> syncls = new SyncRunList<TestModel>(ipdpOrders){
            @Override
            protected void runItem(TestModel item) {
                doTransaction(item);
            }
            @Override
            protected void afterRun(SyncRunnb syncRunnb) {
                countDownLatch.countDown();
            }
        };
        try{
            long startTime = System.currentTimeMillis();
            syncls.executorTasks(taskExecutor,totalThreadNum);
            countDownLatch.await();
            long endTime = System.currentTimeMillis();
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        }catch (Exception e){
            logger.info("发送销售平台订单信息批量处理主线程等待发生异常：" + e.getMessage());
        }
    }

    public void sendOrderBatch2(TestModel ipdpOrders){

        SyncRunnbSingleton syncRunnbSingleton = new SyncRunnbSingleton(ipdpOrders);

        ExecutorService executorService = Executors.newCachedThreadPool();
        //taskExecutor.execute(syncRunnbSingleton);
        executorService.execute(syncRunnbSingleton);

    }

    public void doTransaction(TestModel model){

        //执行时间越多，多线程执行时间越长
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e1) {
        }
        System.out.println("CoreSize:" + model.getCoreSize()+ "  QueueCapacity:" + model.getQueueCapacity());
    }
}
