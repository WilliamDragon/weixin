package com.gjl.weixin.controller;

import com.gjl.weixin.test.concurrency.TaskExecutor1;
import com.gjl.weixin.test.concurrency.TaskExecutor2;
import com.gjl.weixin.test.concurrency.TestModel;
import com.gjl.weixin.test.concurrency.xpad.TestSyncRunList;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author WilliamDragon
 * @Date 2021/4/25 14:56
 * @Version 1.0
 */
@RestController
public class TestModelController {

    @Autowired
    private TaskExecutor1 taskExecutor1;
    @Autowired
    private TaskExecutor2 taskExecutor2;
    @Autowired
    private TestSyncRunList testSyncRunList;
    @GetMapping("/testModelMethid")
    public R TestModelMethid() {
        try{
            //taskExecutor1.dealTransaction();
            //taskExecutor2.dealTransaction();

            List<TestModel> testModelList2 = new ArrayList<>();
            for(int i=1;i<50;i++){
                TestModel testModelTemp2 = new TestModel();
                testModelTemp2.setQueueCapacity(i);
                testModelTemp2.setCoreSize(Integer.toString(i));
                testModelList2.add(testModelTemp2);
            }
           // testSyncRunList.sendOrderBatch(testModelList2);

            testModelList2.parallelStream().forEach(x->doTran(x));

        }catch (Exception e){
            e.printStackTrace();
        }

        return R.ok();
    }

    public void doTran(TestModel testModel){

        testSyncRunList.sendOrderBatch2(testModel);
    }
}
