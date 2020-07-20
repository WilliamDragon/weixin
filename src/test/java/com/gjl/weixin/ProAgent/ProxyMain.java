package com.gjl.weixin.ProAgent;

import com.gjl.weixin.ProAgent.impl.DynamicProxy;
import com.gjl.weixin.ProAgent.impl.Vendor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyMain {
    public static void main(String[] args) {
        //声明动态代理初始化  创建中介类实例
        DynamicProxy inter = new DynamicProxy(new Vendor());
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");


        //获取代理类的sell
       // Sell sell = (Sell) (Proxy.newProxyInstance(Sell.class.getClassLoader(), new Class[]{Sell.class},  inter));
        //sell.sell();

    }
}
