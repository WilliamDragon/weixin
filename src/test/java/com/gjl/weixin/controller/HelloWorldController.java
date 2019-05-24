package com.gjl.weixin.controller;

import com.gjl.weixin.entity.Complain;
import com.gjl.weixin.mapper.ComplainMapper;
import com.gjl.weixin.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/24 14:56
 * @Version 1.0
 */
@Controller
public class HelloWorldController {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

}
