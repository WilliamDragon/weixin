package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.cache.CodeCache;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    //根据学生userName和card_id登录
    @PostMapping("/login")
    public R login(String userName, String password, HttpSession httpSession){
        List<Student> list=studentService.login(userName,password);
        if(list.size()>0){
            httpSession.setAttribute("userInfo",list.get(0));
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }
    @PostMapping("/findByCardId")
    public  boolean findByCardId(String cardId){
        List<Student> list=studentMapper.findByCardId(cardId);
        return list.size()>0 ? true:false;
    }

    @GetMapping("/findAll")
    public R findAll(){
        List<StudentDto> list=studentService.findAll();
        if(list.size()>0){
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }

    //查询所以用户
    @GetMapping("/findAll2")
    public R findAll2(String pageNum, String pageSize, Model model){

        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }

        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));

        List<Student> list=studentMapper.findAll2();
        PageInfo pageInfo = new PageInfo<Student>(list, 3);

        List<Student> list1 = pageInfo.getList();
        if(list1.size()>0){
            return R.ok(pageInfo);
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


    //更新用户
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
    //根据用户id删除
    @GetMapping("/delete")
    public R delete(String id){
        int list = studentService.deleteById(id);
        if(list>0){
            return R.ok(list);
        }
        return R.error("删除失败");
    }

    //新增用户
    @Autowired
    private PxclassMapper pxclassMapper;
    @GetMapping("/insert")
    public R insert(Student student,String pxclassName){

        if(findByCardId(student.getCardId())){
            return R.error("用户已存在");
        }
       List<Pxclass> list = pxclassMapper.findPxclassByName(pxclassName);
        if(list.size()==0){
            return R.error("培训班不存在");
        }
        Long id=list.get(0).getId();
        student.setPxclassId(id);

        int i = studentMapper.insertSelective(student);
        if(i>0){
            return R.ok(list);
        }
        return R.error("插入失败");
    }

    //批量插入用户调查问卷
    @Autowired
    private StatisticMapper statisticMapper;
    @GetMapping("/questionCount")
    public R questionCount(QuestionnaireDto questionnaireDto, HttpServletRequest request){

        logger.debug("进入统计方法");
        System.out.println("进入统计方法");
        HttpSession session=request.getSession();
        //查询当前用户信息
        Student userInfo = (Student)session.getAttribute("userInfo");

        List<Statistic> list= new ArrayList<Statistic>();

        for (int i=0;i<20;i++){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate=sdf.format(date);
            Statistic statistic = new Statistic();
            statistic.setCreateTime(currentDate);
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
