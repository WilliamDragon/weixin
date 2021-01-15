package com.gjl.weixin.service.impl;

import com.gjl.weixin.entity.Notice;
import com.gjl.weixin.mapper.NoticeMapper;
import com.gjl.weixin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int insertNotice(Notice notice) {

        System.out.println(notice.getNoticeSpare());
        notice.setNoticeSpare("sef");
        System.out.println(notice.getNoticeSpare());


       /* int result = noticeMapper.insertSelective(notice);
        int i =1/0;
        return result;*/
        return 1;
    }

    @Override
    public int updateNotice(Notice notice) {
        return 0;
    }
}
