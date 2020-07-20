package com.gjl.weixin.ProAgent.impl;

import com.gjl.weixin.ProAgent.Sell;
//代理类（静态代理）
public class BusinessAgent implements Sell {

    private Sell vendor;
    public BusinessAgent(Sell vendor){
        this.vendor = vendor;
    }
    //好处就是，可以在代理方法里面过滤一些东西
    //比如如果是学生才能售卖
    @Override
    public void sell() {
        System.out.println("before()");
        if(/*isStudent()*/true ){
            vendor.sell();
        }
        System.out.println("after()");
    }

    @Override
    public void ad() {

    }
}
