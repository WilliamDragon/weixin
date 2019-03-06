package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Statistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatisticMapper {
    int insert(Statistic record);

    int insertSelective(Statistic record);

    //@Select("select question_id,answer from statistic s join pxclass p on  s.pxclass_id=p.id and p.class_name=#{className}")
    List<Statistic> findStatisticByGroupPxclass(@Param("className") String className);

    int insertByBatch(List<Statistic> list);
}