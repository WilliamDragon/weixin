package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Pxclass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PxclassMapper {
    int insert(Pxclass record);

    int insertSelective(Pxclass record);

    Pxclass findPxclassById(String id);

    List<Pxclass> findAll();

    List<Pxclass> findPxclassByName(String pxclassName);

    @Delete("delete from pxclass where id = #{id}")
    int deleteById(@Param("id") String id);
}