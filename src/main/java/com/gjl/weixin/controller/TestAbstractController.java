package com.gjl.weixin.controller;

import com.gjl.weixin.mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author WilliamDragon
 * @Date 2021/4/23 10:17
 * @Version 1.0
 */

@Component
public abstract class TestAbstractController {

    @Autowired
    ComplainMapper complainMapper;
    public void testhhh(){};
}
