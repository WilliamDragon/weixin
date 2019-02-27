package com.gjl.weixin.service;

import com.gjl.weixin.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> login(String userName, String password);
}
