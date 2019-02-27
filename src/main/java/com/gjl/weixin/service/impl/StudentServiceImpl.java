package com.gjl.weixin.service.impl;

import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.StudentMapper;
import com.gjl.weixin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> login(String userName, String password) {
        List<Student> list=studentMapper.login(userName,password);
        return list;
    }
}
