package com.gjl.weixin.controller;

import com.gjl.weixin.service.MycountDownLatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WilliamDragon
 * @Date 2021/2/22 11:10
 * @Version 1.0
 */
@RestController
public class MycountDownLatchController {  

    @Autowired
    private MycountDownLatchService mycountDownLatchService;
    @GetMapping("/testCountDownLatch")
    public void testCountDownLatch(String tableName){
        mycountDownLatchService.testMycountDownLatchService(tableName);
    }
}
