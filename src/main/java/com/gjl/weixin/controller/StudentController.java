package com.gjl.weixin.controller;

import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.service.StudentService;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    //根据学生userName和card_id登录
    @PostMapping("/login")
    public R login(String userName,String password){
        List<Student> list=studentService.login(userName,password);
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

}
