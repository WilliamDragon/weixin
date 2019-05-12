package com.gjl.weixin.controller;

import com.gjl.weixin.controller.export.ExportWord;
import com.gjl.weixin.entity.Statistic;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.service.StatisticService;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private ExportWord exportWord;
    //统计表格输出aaa.doxc
    @RequestMapping("/count")
    public R count(String className, HttpServletRequest request, HttpServletResponse response){

        List<Statistic> list = statisticService.findStatisticByGroupPxclass(className);

        int total = statisticService.findTotalByClassName()/20;
        //二维数组顺序代表统计表格
        int[][] ints = new int[20][5];

        for(int i=0;i<ints.length;i++){
            String questionOrder = String.valueOf((i+1));
            ints[i][0]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"a".equals(x.getAnswer()))).count();
            ints[i][1]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"b".equals(x.getAnswer()))).count();
            ints[i][2]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"c".equals(x.getAnswer()))).count();
            ints[i][3]=(int)list.stream().filter(x->
                    (questionOrder.equals(String.valueOf(x.getQuestionId()))&&"d".equals(x.getAnswer()))).count();
            ints[i][4]=(ints[i][0]*115+ints[i][1]*100+ints[i][3]*80)/total;
        }
        /*int question1a=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"a".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1b=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"b".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1c=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"c".equals(x.getAnswer()))).collect(Collectors.toList()).size();
        int question1d=list.stream().filter(x->
                ("1".equals(String.valueOf(x.getQuestionId()))&&"d".equals(x.getAnswer()))).collect(Collectors.toList()).size();
*/

        exportWord.export(request,response,ints,className);
        System.out.println(ints);
         if(ints.length>0){
             return R.ok();
        }
        return R.error();
    }
}
