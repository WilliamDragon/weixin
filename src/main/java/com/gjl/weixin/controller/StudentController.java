package com.gjl.weixin.controller;

import com.gjl.weixin.dto.QuestionnaireDto;
import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Pxclass;
import com.gjl.weixin.entity.Statistic;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.PxclassMapper;
import com.gjl.weixin.mapper.StatisticMapper;
import com.gjl.weixin.mapper.StudentMapper;
import com.gjl.weixin.service.StudentService;
import com.gjl.weixin.utils.R;
import org.checkerframework.checker.signature.qual.BinaryNameForNonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    //根据学生userName和card_id登录
    @PostMapping("/login")
    public R login(String userName, String password, HttpSession httpSession){
        List<Student> list=studentService.login(userName,password);
        httpSession.setAttribute("userInfo",list.get(0));
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }

    @GetMapping("/findAll")
    public R findAll(){
        List<StudentDto> list=studentService.findAll();
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }

    @Autowired
    private StudentMapper studentMapper;
    @GetMapping("/findAll2")
    public R findAll2(){
        List<Student> list=studentMapper.findAll2();
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }
    @GetMapping("/findStudentById")
    public R findStudentById(String id){
        List<Student> list=studentMapper.findAll2();
        List<Student> collect = list.stream().filter(s -> String.valueOf(s.getId()).equals(id)).collect(Collectors.toList());
        if(collect.size()>0){
            return R.ok(collect);
        }
        return R.error("用户名或密码错误");
    }


    @GetMapping("/save")
    public R save(Student student,String className){

        List<Pxclass> list = pxclassMapper.findPxclassByName(className);
        Long id=list.get(0).getId();
        student.setPxclassId(id);
        System.out.println(student);
        int i = studentMapper.updateSelective(student);

        if(i>0){
            return R.ok(list);
        }
        return R.error("保存失败");
    }
    @GetMapping("/delete")
    public R delete(String id){
        int list = studentService.deleteById(id);
        if(list>0){
            return R.ok(list);
        }
        return R.error("删除失败");
    }

    @Autowired
    private PxclassMapper pxclassMapper;
    @GetMapping("/insert")
    public R insert(Student student,String pxclassName){

        List<Pxclass> list = pxclassMapper.findPxclassByName(pxclassName);
        Long id=list.get(0).getId();
        student.setPxclassId(id);

        int i = studentMapper.insertSelective(student);
        if(i>0){
            return R.ok(list);
        }
        return R.error("插入失败");
    }

    @Autowired
    private StatisticMapper statisticMapper;
    @GetMapping("/questionCount")
    public R questionCount(QuestionnaireDto questionnaireDto, HttpServletRequest request){
        HttpSession session=request.getSession();
        Student userInfo = (Student)session.getAttribute("userInfo");

        List<Statistic> list= new ArrayList<Statistic>();
        for (int i=0;i<20;i++){
            Statistic statistic = new Statistic();
            statistic.setCreateTime("2019-01-12 12:12:12");
            statistic.setCreateTime("2019-01-12 12:12:12");
            statistic.setStudentId(userInfo.getId());
            statistic.setPxclassId(userInfo.getPxclassId());
            Long QuestionId=Long.valueOf((i+1));
            statistic.setQuestionId(QuestionId);
            switch (i){
                case 0:
                    statistic.setAnswer(questionnaireDto.getQuestion1());break;
                case 1:
                    statistic.setAnswer(questionnaireDto.getQuestion2());break;
                case 2:
                    statistic.setAnswer(questionnaireDto.getQuestion3());break;
                case 3:
                    statistic.setAnswer(questionnaireDto.getQuestion4());break;
                case 4:
                    statistic.setAnswer(questionnaireDto.getQuestion5());break;
                case 5:
                    statistic.setAnswer(questionnaireDto.getQuestion6());break;
                case 6:
                    statistic.setAnswer(questionnaireDto.getQuestion7());break;
                case 7:
                    statistic.setAnswer(questionnaireDto.getQuestion8());break;
                case 8:
                    statistic.setAnswer(questionnaireDto.getQuestion9());break;
                case 9:
                    statistic.setAnswer(questionnaireDto.getQuestion10());break;
                case 10:
                    statistic.setAnswer(questionnaireDto.getQuestion11());break;
                case 11:
                    statistic.setAnswer(questionnaireDto.getQuestion12());break;
                case 12:
                    statistic.setAnswer(questionnaireDto.getQuestion13());break;
                case 13:
                    statistic.setAnswer(questionnaireDto.getQuestion14());break;
                case 14:
                    statistic.setAnswer(questionnaireDto.getQuestion15());break;
                case 15:
                    statistic.setAnswer(questionnaireDto.getQuestion16());break;
                case 16:
                    statistic.setAnswer(questionnaireDto.getQuestion17());break;
                case 17:
                    statistic.setAnswer(questionnaireDto.getQuestion18());break;
                case 18:
                    statistic.setAnswer(questionnaireDto.getQuestion19());break;
                case 19:
                    statistic.setAnswer(questionnaireDto.getQuestion20());break;
            }
            list.add(statistic);

        }

        System.out.println(list);

        int i= statisticMapper.insertByBatch(list);
        if(i>0){
            return R.ok(list);
        }
        return R.error("插入失败");
    }

}
