package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Pxclass;

public interface PxclassMapper {
    int insert(Pxclass record);

    int insertSelective(Pxclass record);

    Pxclass findPxclassById(String id);
}