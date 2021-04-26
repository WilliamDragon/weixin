package com.gjl.weixin.test.concurrency;

import com.gjl.weixin.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author WilliamDragon
 * @Date 2021/4/25 14:25
 * @Version 1.0
 */
@Service
public class TaskExecutor1 {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TaskExecutor taskExecutor;

    private CountDownLatch doSgnl;

    private static int totalThreadNum = 50;
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
        //以上testModelList 用来模拟从数据库查询到得结果

        List<TestModel> testModelList2 = new ArrayList<>();
        for(int i=1;i<50;i++){
            TestModel testModelTemp2 = new TestModel();
            testModelTemp2.setQueueCapacity(i);
            testModelList2.add(testModelTemp2);
        }

        List<ModelShell> shells = new ArrayList<ModelShell>();
        CountDownLatch cdl = new CountDownLatch(testModelList2.size());
        doSgnl = new CountDownLatch(totalThreadNum);
        for (int n = 0; n < totalThreadNum; n++) {
            ModelShell atdShell = new ModelShell(testModelList2, cdl);
            shells.add(atdShell);
        }

        long startTime = System.currentTimeMillis();
        try {
            int slSize = shells.size();
            if (slSize > 0) {
                for (int i = 0; i < slSize;) {
                    try {
                        taskExecutor.execute(shells.get(i));
                        i++;
                    } catch (TaskRejectedException e) {
                        System.out.println("1"+"@@"+"任务不能被接受。");
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e1) {
                        }
                    }
                }
                doSgnl.await();
            }
        } catch (InterruptedException e) {
            System.out.println("自动续约发生异常：");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

    }



    class ModelShell implements Runnable{

        List<TestModel> modelList;
        CountDownLatch cdl;
        public ModelShell(List<TestModel> modelList, CountDownLatch countDownLatch){
            this.modelList = modelList;
            this.cdl = countDownLatch;
        }
        @Override
        public void run() {
            try{
                while(true){
                    int curCount = 0;
                    TestModel model = null;
                    synchronized (cdl) {
                        curCount = (int) cdl.getCount();
                        if (curCount == 0) {
                            break;
                        }
                        model = modelList.get(curCount - 1);
                        cdl.countDown();
                    }
                    //真正处理业务得方法
                    doTransaction(model);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                doSgnl.countDown();
            }
        }
    }

    public void doTransaction(TestModel model){

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e1) {
        }
        System.out.println("CoreSize" + model.getCoreSize());
    }
}
