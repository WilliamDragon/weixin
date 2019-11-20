package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Permission;
import com.gjl.weixin.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/11/11 17:31
 * @Version 1.0
 */
@Mapper
public interface PermissionMapper {
    List<Permission> findUserPerm(String username);
}
