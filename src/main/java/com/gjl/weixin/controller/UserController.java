package com.gjl.weixin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjl.weixin.cache.GlobalCache;
import com.gjl.weixin.entity.*;
import com.gjl.weixin.mapper.PermissionMapper;
import com.gjl.weixin.mapper.RoleMapper;
import com.gjl.weixin.mapper.UserMapper;
import com.gjl.weixin.utils.MD5Util;
import com.gjl.weixin.utils.R;
import com.gjl.weixin.utils.SendMailUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/adminUser")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GlobalCache globalCache;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/loginindex")
    public String loginindex(){
        return "adminlogin";
    }
    @RequestMapping("/qwe")
    public String test1(){
        return "indexx";
    }
    @RequestMapping("dept")
    public void test(){
        User user = new User();
        user.setLoginName("sdf");
        userMapper.insert(user);
        //System.out.println(list);
    }

    @GetMapping("/save")
    public R save(User user, HttpServletRequest request){
        logger.debug("进入 save 方法");
        HttpSession session=request.getSession();
        List<User> all = userMapper.findAll();
        List<User> collect = all.stream().filter(x -> (
                String.valueOf(x.getId()).equals(user.getId().toString())
        )).collect(Collectors.toList());
        if(!user.getStatus().equals(collect.get(0).getStatus())){
            User userInfo =(User) session.getAttribute("userInfo");
            if(!userInfo.getLoginName().equals("111")){
                return R.error("您没有权限修改状态，请联系超级管理员");
            }
        }

        String str = MD5Util.getMD5Code(user.getPassword()+"guojinlong");
        user.setPassword(str);
        int list = userMapper.save(user);
        if(list>0){
            return R.ok(list);
        }
        return R.error("更新失败");
    }

    @GetMapping("/findUserById")
    public R findUserById(String id){
        logger.debug("进入 findUserById 方法");
        List<User> all = userMapper.findAll();
        List<User> collect = all.stream().filter(x -> (
            String.valueOf(x.getId()).equals(id)
        )).collect(Collectors.toList());
        if(collect.size()>0){
            return R.ok(collect);
        }
        return R.error("查询失败");
    }
    @GetMapping("/delete")
    public R delete(String id){
        logger.debug("进入 delete 方法");
        int list = userMapper.deleteById(id);
        if(list>0){
            return R.ok(list);
        }
        return R.error("删除失败");
    }
    @GetMapping("findAllByCondition")
    public R findAllByCondition(String pageNum, String pageSize,User user){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<User> list = userMapper.findAllByCondition(user);
        PageInfo pageInfo = new PageInfo<User>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("用户不存在");
    }
    @GetMapping("findAll")
    public R findAll(String pageNum, String pageSize){
        if(pageNum==null){
            pageNum="1";
        }
        if(pageSize==null){
            pageSize="3";
        }
        PageHelper.startPage( Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        List<User> list = userMapper.findAll();
        PageInfo pageInfo = new PageInfo<User>(list, 3);
        if(list.size()>0){
            return R.ok(pageInfo);
        }
        return R.error("用户不存在");
    }

    @GetMapping("insert")
    public R insert(User user) {
        try{
           // SendMailUtil.SendMail("2645019356@qq.com","测试zhuti","测试neirong");
        }catch (Exception e){
            e.printStackTrace();
        }
        String str = MD5Util.getMD5Code(user.getPassword()+"guojinlong");
        user.setPassword(str);
        user.setCreateTime(new Date());
        user.setStatus("0");
        int i = userMapper.insertSelective(user);
        if(i>0){
            return R.ok();
        }
        return R.error("新增失败");
    }
    @PostMapping("/login")
    @ResponseBody
    public R login(String userName, String password, HttpSession httpSession, HttpServletResponse response){
        /*logger.debug("进入 login 方法");
        String str = MD5Util.getMD5Code(password+"guojinlong");
        List<User> list=userMapper.login(userName,str);
        if(list.size()>0){
            globalCache.add("userInfo",list.get(0));
            httpSession.setAttribute("userInfo",list.get(0));
            return R.ok(list);
        }
        return R.error("用户名或密码错误");*/
        String pass = MD5Util.getMD5Code(password+"guojinlong");
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pass);
        try{
            Subject subject = SecurityUtils.getSubject();
            if(subject != null){
                subject.logout();
            }
            subject.login(token);
            User user0 = (User)SecurityUtils.getSubject().getPrincipal();

            List<Permission> permissions = new ArrayList<Permission>();
            List<Permission> userPermlist = permissionMapper.findUserPerm(user0.getLoginName());
            for(Permission p : userPermlist){
                permissions.add(p);
            }

            List<Role> roles = new ArrayList<Role>();
            List<Role> userRolelist = roleMapper.findUserRole(user0.getLoginName());

            for(Role r :userRolelist){
                roles.add(r);
            }
            user0.setRole(roles);
            user0.setPermission(permissions);
            return R.ok(user0);

        }catch(Exception e){
            e.printStackTrace();
        }
        return R.error();
    }

    @GetMapping("/testGlobalCache")
    @ResponseBody
    public R testGlobalCache(){
        System.out.println(globalCache.get("gjl"));
        globalCache.add("gjl","gjl");
        System.out.println(globalCache.get("gjl"));

        return R.ok();
    }
    @PostMapping("/testUserRole")
    @ResponseBody
    public R testUserRole(String username){
        List<Permission> userPerm = permissionMapper.findUserPerm(username);
        if(userPerm != null){
            return R.ok(userPerm);
        }
        return R.ok("用户没有设定角色");
    }

    public static void main(String[] args) {

        String str = MD5Util.getMD5Code("111"+"guojinlong");
        System.out.println(str);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultFinalRate = df.format(52.03);
        System.out.println(resultFinalRate);
        /*List<String> a=new ArrayList<String>();
        a.add("q");a.add("w");a.add("e");
        List<String> b=new ArrayList<String>();
        b.add("a");b.add("s");b.add("d");
        List<String> list=new ArrayList<String>();

        list=Stream.of(a,b).flatMap(s->s.stream()).sorted().collect(Collectors.toList());

        for(String item:list){
            System.out.println(item);
        }
*/


    }

}
