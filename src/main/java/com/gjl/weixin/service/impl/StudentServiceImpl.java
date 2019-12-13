package com.gjl.weixin.service.impl;

import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.StudentMapper;
import com.gjl.weixin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    @Override
    public List<StudentDto> findAll() {
        List<StudentDto> list=studentMapper.findAll();
        return list;
    }

    @Override
    public int save(Integer id) {
        return studentMapper.save(id);
    }

    @Override
    public int deleteById(String id) {
        return studentMapper.delete(id);
    }

    @Transactional
    public int insertStudent(Student student){
        int result = studentMapper.insert(student);
        //throw new IOException();
       /* try{
            throw new  RuntimeException("发生异常");
        }catch (Exception e){
            e.printStackTrace();
        }*/

        return result;
    }
}
