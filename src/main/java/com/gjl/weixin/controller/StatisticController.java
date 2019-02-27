package com.gjl.weixin.controller;

import com.gjl.weixin.entity.Statistic;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.service.StatisticService;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping("/count")
    public R count(String className){

        List<Statistic> list = statisticService.findStatisticByGroupPxclass(className);

        int question1a=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"a".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1b=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"b".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1c=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"c".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1d=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"d".equals(x.getAnswer()))).collect(Collectors.toList()).size();


         if(question1a>0){
             return R.ok();
        }
        return R.error();
    }
}
