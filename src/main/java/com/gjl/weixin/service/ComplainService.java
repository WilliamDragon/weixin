package com.gjl.weixin.service;

import com.gjl.weixin.dto.ComplainDto;
import com.gjl.weixin.entity.Complain;

import java.util.List;
import java.util.Map;

public interface ComplainService {

    int insertComplain(Complain complain);
    int updateComplain(Complain complain);
    public List<ComplainDto> findAllComplain(Map map);
}
