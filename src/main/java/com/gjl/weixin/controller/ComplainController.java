package com.gjl.weixin.controller;

import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R insert(Complain complain){
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
