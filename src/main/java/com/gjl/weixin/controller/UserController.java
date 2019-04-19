package com.gjl.weixin.controller;

import com.gjl.weixin.cache.GlobalCache;
import com.gjl.weixin.entity.User;
import com.gjl.weixin.mapper.UserMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GlobalCache globalCache;


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
    public R login(String userName, String password, HttpSession httpSession){
        List<User> list=userMapper.login(userName,password);
        if(list.size()>0){
            httpSession.setAttribute("adminUserInfo",list.get(0));
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
