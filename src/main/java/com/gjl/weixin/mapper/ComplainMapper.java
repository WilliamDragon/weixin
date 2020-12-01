package com.gjl.weixin.mapper;

import com.gjl.weixin.dto.ComplainDto;
import com.gjl.weixin.entity.Complain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ComplainMapper {
    int insert(Complain record);

    int insertSelective(Complain record);

    int insertSelective2(Complain record);

    List<Complain> findAll();

    int updateComplainById(String id);

    List<ComplainDto> findAllByAdmin(ComplainDto complainDto);

    List<ComplainDto> findAllSelect(Map map);
}