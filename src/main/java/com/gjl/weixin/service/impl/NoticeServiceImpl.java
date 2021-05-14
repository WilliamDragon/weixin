package com.gjl.weixin.service.impl;

import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.entity.Notice;
import com.gjl.weixin.mapper.NoticeMapper;
import com.gjl.weixin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ComplainServiceImpl complainService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int insertNotice(Notice notice) {

        /*System.out.println(notice.getNoticeSpare());
        notice.setNoticeSpare("12345678");
        notice.setNoticeContent("12345");
        System.out.println(notice.getNoticeSpare());
        Notice notice1 = noticeMapper.findNotice(notice);
        Integer noticeContent1 = Integer.valueOf(notice1.getNoticeContent())-1;
        if(noticeContent1<0){
            return 0;
        }
        notice.setNoticeContent(noticeContent1.toString());
        int result = noticeMapper.updateNotice(notice);*/
         int result = noticeMapper.insertSelective(notice);

        List<Notice> list=noticeMapper.findAllNotice(notice);
        Complain complain = new Complain();
        complainService.insertComplain(complain);
        //int i =1/0;
        return result;
       /* List<Notice> noticeList = new ArrayList<Notice>();
        Notice notice1 = new Notice();
        Notice notice2 = new Notice();
        Notice notice3 = new Notice();
        notice1.setNoticeDate("20210302");
        notice1.setNoticeSpare("20210302");
        notice1.setNoticeContent("20210302");

        notice2.setNoticeDate("20210303");
        notice2.setNoticeSpare("20210303");
        notice2.setNoticeContent("20210303");

        notice3.setNoticeDate("20210304");
        notice3.setNoticeSpare("20210304");
        notice3.setNoticeContent("20210304");
        noticeList.add(notice1);
        noticeList.add(notice2);
        noticeList.add(notice3);
        int result = noticeMapper.batchInsert(noticeList);
        return result;*/
       // return 1;
    }

    @Override
    public int updateNotice(Notice notice) {
        return 0;
    }
}
