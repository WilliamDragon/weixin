package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NoticeMapper {
    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> findAllNotice(Notice notice);

    int updateNotice(Notice record);

    int batchInsert(@Param("noticeList")List<Notice> noticeList);
}