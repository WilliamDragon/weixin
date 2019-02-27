package com.gjl.weixin.mapper;

import com.gjl.weixin.dto.StudentDto;
import com.gjl.weixin.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    int insert(Student record);

    int insertSelective(Student record);

    List<Student> login(@Param("userName") String userName, @Param("password")String password);

    List<StudentDto> findAll();
}