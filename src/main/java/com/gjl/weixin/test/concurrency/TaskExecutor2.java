package com.gjl.weixin.test.concurrency;

import com.gjl.weixin.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author WilliamDragon
 * @Date 2021/4/25 14:25
 * @Version 1.0
 */
@Service
public class TaskExecutor2 {

    @Autowired
    private TaskExecutor taskExecutor;

    private static int totalThreadNum = 10;
    //处理业务
    public void dealTransaction() throws InterruptedException {
        TestModel testModel1 = new TestModel();
        testModel1.setCoreSize("12");
        TestModel testModel2 = new TestModel();
        testModel2.setCoreSize("34");
        TestModel testModel3 = new TestModel();
        testModel3.setCoreSize("45");
        TestModel testModel4 = new TestModel();
        testModel4.setCoreSize("67");
        TestModel testModel5 = new TestModel();
        testModel5.setCoreSize("89");
        List<TestModel> testModelList = new ArrayList<>();
        testModelList.add(testModel1);
        testModelList.add(testModel2);
        testModelList.add(testModel3);
        testModelList.add(testModel4);
        testModelList.add(testModel5);

        List<TestModel> testModelList2 = new ArrayList<>();
        for(int i=1;i<50;i++){
            TestModel testModelTemp2 = new TestModel();
            testModelTemp2.setQueueCapacity(i);
            testModelList2.add(testModelTemp2);
        }
        //以上testModelList 用来模拟从数据库查询到得结果
        //把testModelList 分成4份，或者有某个字段区别
        CountDownLatch threadSignal = new CountDownLatch(2);
        new Thread(new ModelShell(testModelList,threadSignal)).start();
        new Thread(new ModelShell(testModelList2,threadSignal)).start();
        //new Thread(new ModelShell(testModelList,threadSignal)).start();
        //new Thread(new ModelShell(testModelList,threadSignal)).start();
        try {
            // 等待子线程完成
            threadSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    class ModelShell implements Runnable{

        List<TestModel> modelList;
        private CountDownLatch countDownLatch;
        public ModelShell(List<TestModel> modelList, CountDownLatch countDownLatch){
            this.modelList = modelList;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            if(modelList.size() == 0){
                countDownLatch.countDown();
                System.out.println("表没有要读取的记录");
                return;
            }
            for(TestModel model : modelList){
                doTransaction(model);
            }
            countDownLatch.countDown();
        }
    }

    public void doTransaction(TestModel model){

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e1) {
        }
        System.out.println("CoreSize" + model.getCoreSize()+ "QueueCapacity" + model.getQueueCapacity());
    }
}
