package com.gjl.weixin.service.impl;

import com.gjl.weixin.dto.ComplainDto;
import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.entity.Notice;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.service.ComplainService;
import com.gjl.weixin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainMapper complainmapper;
    @Autowired
    private NoticeService noticeService;
    int num = 0;
    static int num2 =0;
    @Override
    //@Transactional(rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int insertComplain(Complain complain) {

        //int result2 = complainmapper.insertSelective(complain);

            /*Notice notice = new Notice();
            notice.setNoticeDate("20210204");
            notice.setNoticeContent("测试事务通知20");
            notice.setNoticeSpare("测试事务通知20");

            noticeService.insertNotice(notice);*/
        //insertComplain2(complain);
       /* try{
           *//* Notice notice = new Notice();
            notice.setNoticeDate("20210204");
            notice.setNoticeContent("测试事务通知20");
            notice.setNoticeSpare("测试事务通知20");

            noticeService.insertNotice(notice);*//*
            insertComplain2(complain);

        }
        catch (Exception e){
            e.printStackTrace();
        }*/
       /* complain.setComplainVideo("insertComplain112");
        System.out.println("ComplainVide:" + complain.getComplainVideo());

        int result = complainmapper.insertSelective(complain);*/
        int number = 0;
        number++;
        num++;
        num2++;
        //System.out.println("==========number:"+ number+"=====num"+num+"=========num2"+num2);
        return 1;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int insertComplain2(Complain complain) {
        if(complain!=null){
            complain.setComplainVideo("insertComplain22222222222");
        }
        int result = complainmapper.insertSelective2(complain);
        //throw new RuntimeException("hit");

        return 1;

    }

    @Override
    public int updateComplain(Complain complain) {
        //toto
        //return complainmapper.updateComplainById();
        return 1;
    }

    public List<ComplainDto> findAllComplain(Map map){

        return complainmapper.findAllSelect(map);
    }
}
