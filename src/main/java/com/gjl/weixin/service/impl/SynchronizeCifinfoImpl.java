package com.gjl.weixin.service.impl;

import com.gjl.weixin.entity.Notice;
import com.gjl.weixin.mapper.NoticeMapper;
import com.gjl.weixin.service.SynchronizeCifinfo;
import com.gjl.weixin.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author WilliamDragon
 * @Date 2021/2/22 9:54
 * @Version 1.0
 */
@Service
public class SynchronizeCifinfoImpl implements SynchronizeCifinfo {

    @Autowired
    private NoticeMapper noticeMapper;
    /**
     * 更新客户信息
     * @param tableName
     * @param cifDate
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCifInfo(String tableName, Date cifDate){
        if("notice1".equals(tableName)){
            int i =1/0;
        }
        Notice notice = new Notice();
        notice.setNoticeContent(tableName);
        System.out.println("====================="+DateUtil.DateToString(cifDate,"yyyy-MM-dd"));
        notice.setNoticeDate(DateUtil.DateToString(cifDate,"yyyy-MM-dd"));
        notice.setNoticeSpare(tableName);
        noticeMapper.updateNotice(notice);
    }
}
