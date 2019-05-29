package com.gjl.weixin.controller;

import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.entity.Student;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.utils.R;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/13 13:47
 * @Version 1.0
 */

@RestController
@RequestMapping("/complain")
public class ComplainController {

    @Autowired
    ComplainMapper complainMapper;
    @PostMapping("/insert")
    public R insert(Complain complain, HttpServletRequest request){
        HttpSession session=request.getSession();
        //查询当前用户信息
        Student userInfo = (Student)session.getAttribute("userInfo");
        if(userInfo == null){
            return R.error("用户未登录");
        }
        complain.setUserId(userInfo.getId().toString());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        complain.setComplainTime(date);
        int i=complainMapper.insert(complain);
        if( i>0 ){
            return R.ok();
        }
        else{
            return R.error("插入失败");
        }
    }
    @RequestMapping("/findAll")
    public R findAll(){
        List<Complain> list=complainMapper.findAll();
        return R.ok(list);
    }


}
