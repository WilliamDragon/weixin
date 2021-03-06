package com.gjl.weixin.service;

import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {
    List<Student> login(String userName, String password);

    List<StudentDto> findAll();

    int save(Integer id);

    int deleteById(String id);

    int insertStudent(Student student);
}
