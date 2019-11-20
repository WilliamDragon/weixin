package com.gjl.weixin.mapper;

import com.gjl.weixin.entity.Role;
import com.gjl.weixin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/11/11 17:31
 * @Version 1.0
 */
@Mapper
public interface RoleMapper {
    List<Role> findUserRole(String username);
}
