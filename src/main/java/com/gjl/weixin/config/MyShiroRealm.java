package com.gjl.weixin.config;

import com.gjl.weixin.entity.Permission;
import com.gjl.weixin.entity.Role;
import com.gjl.weixin.entity.User;
import com.gjl.weixin.mapper.PermissionMapper;
import com.gjl.weixin.mapper.RoleMapper;
import com.gjl.weixin.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        log.info("-----------权限认证--------");
        User user = (User)getAvailablePrincipal(principal);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> list = roleMapper.findUserRole(user.getLoginName());
        Set<String> set = new HashSet<>();
        for(Role r : list){
            set.add(r.getRoleName());
        }
        Set<String> pers = new HashSet<>();
        List<Permission> userPermlist = permissionMapper.findUserPerm(user.getLoginName());
        for(Permission P : userPermlist){
            pers.add(P.getPermissUrl());
        }
        //添加角色
        info.setRoles(set);
        //添加权限
        info.setStringPermissions(pers);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authentication) throws AuthenticationException {
        log.info("--------------身份认证方法--------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authentication;
        String nikename = token.getUsername();
        if(nikename == null){
            log.info("Nll usernames are not allowed by this realm.");
        }
        User user = this.userMapper.findByUserName(nikename);
        if(user == null){
            log.info("No account found for admin ["+ nikename +"]");
            throw new RuntimeException("No account found for admin [\"+ nikename +\"]\"");
        }
        if ("0".equals(user.getStatus())) {
            log.info("您的账号被禁止登录，请联系管理员");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return simpleAuthenticationInfo;
    }
}
