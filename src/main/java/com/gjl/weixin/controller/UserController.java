package com.gjl.weixin.controller;

import com.gjl.weixin.cache.GlobalCache;
import com.gjl.weixin.entity.User;
import com.gjl.weixin.mapper.UserMapper;
import com.gjl.weixin.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/adminUser")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GlobalCache globalCache;

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
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


    @PostMapping("/login")
    public R login(String userName, String password, HttpSession httpSession, HttpServletResponse response){
        logger.debug("进入 login 方法");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        List<User> list=userMapper.login(userName,password);
        if(list.size()>0){
            globalCache.add("userInfo",list.get(0));
            httpSession.setAttribute("userInfo",list.get(0));
            return R.ok(list);
        }
        return R.error("用户名或密码错误");
    }

    @GetMapping("/testGlobalCache")
    public R testGlobalCache(){
        System.out.println(globalCache.get("gjl"));
        globalCache.add("gjl","gjl");
        System.out.println(globalCache.get("gjl"));

        return R.ok();
    }

    public static void main(String[] args) {
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
