package com.gjl.weixin.controller;

import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.StudentMapper;
import com.gjl.weixin.service.StudentService;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
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
    public R save(Integer id){
        int list=studentService.save(id);
        if(list>0){
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

    @GetMapping("/insert")
    public R insert(Student student){
        /*int list = studentService.deleteById(id);
        if(list>0){
            return R.ok(list);
        }*/
        return R.error("删除失败");
    }

}
