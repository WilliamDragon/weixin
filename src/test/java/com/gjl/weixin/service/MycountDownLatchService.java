package com.gjl.weixin.service;

import com.gjl.weixin.cache.CodeCache;
import com.gjl.weixin.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author WilliamDragon
 * @Date 2021/2/22 9:22
 * @Version 1.0
 */

public class MycountDownLatchService {

    @Autowired
    private SynchronizeCifinfo synchronizeCifinfo;

    private static final Logger logger = LoggerFactory.getLogger(MycountDownLatchService.class);
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
         * @param date
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
