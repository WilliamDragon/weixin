package com.gjl.weixin.service.impl;

import com.gjl.weixin.service.MycountDownLatchService;
import com.gjl.weixin.service.SynchronizeCifinfo;
import com.gjl.weixin.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author WilliamDragon
 * @Date 2021/2/22 9:58
 * @Version 1.0
 */
@Service
public class MycountDownLatchServiceImpl implements MycountDownLatchService {

    private static final Logger logger = LoggerFactory.getLogger(MycountDownLatchServiceImpl.class);

    List<String> filelist = new ArrayList<String>();
    @Override
    public void testMycountDownLatchService(String tableName) {
        List<String> filelist = new ArrayList<String>();
        Date cifdate = new Date();
        CountDownLatch threadSignal = new CountDownLatch(2); //开启5读个线程，分别CUID、CUSM、CUSVAA、CUSTSYN、CUMI

        new Thread(new BanchCifData(filelist, "notice1", cifdate, threadSignal)).start();
        new Thread(new BanchCifData(filelist, "notice2", cifdate, threadSignal)).start();
        try {
            // 等待子线程完成
            threadSignal.await();
        } catch (InterruptedException e) {
            logger.info("-1"+"@@"+"CIF客户数据处理异常结束："+e);
            e.printStackTrace();
        }
    }

    @Autowired
    private SynchronizeCifinfo synchronizeCifinfo;

    //晚批中每天同步核心客户信息，进而更新到本地数据库，采用多线程方式
    private class BanchCifData implements Runnable{
        private List<String> fileList;//测试list集合的处理
        private String tableName;//测试String的处理
        private Date cifDate;//处理时间
        private CountDownLatch countDownLatch;

        /**
         * 初始化多线程处理
         * @param filelist
         * @param tableName
         * @param cifDate
         * @param countDownLatch
         */
        public BanchCifData(List<String> filelist, String tableName, Date cifDate, CountDownLatch countDownLatch){
            this.fileList = filelist;
            this.tableName = tableName;
            this.cifDate = cifDate;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            logger.info(tableName + "客户信息持久化线程启动执行");
            if(StringUtil.isNullOrEmpty(tableName)){
                countDownLatch.countDown();
                logger.info(tableName + "没有客户信息需要持久化操作");
                return ;
            }

            synchronizeCifinfo.updateCifInfo(tableName, cifDate);
            countDownLatch.countDown();
            logger.info(tableName+"表持久化线程执行结束。");

        }
    }

}
