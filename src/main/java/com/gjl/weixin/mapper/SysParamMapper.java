package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Student;
import com.gjl.weixin.entity.SysParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysParamMapper {
    int insert(SysParam record);

    int insertSelective(SysParam record);

    @Select("select * from sysparam")
    List<SysParam> findSysParamAll();

    @Select("select * from student where id = #{sysParamId}")
    List<SysParam> findByCardId(String sysParamId);

    @Delete("delete  from sysparam where id=#{id}")
    int deleteById(@Param("id") String id);

    @Update("update sysparam set sysvalue = #{sysvalue} where id = #{id}")
    int save(SysParam record);

    @Select("select * from sysparam where id = #{id}")
    List<SysParam> findSysParamById(String id);
}