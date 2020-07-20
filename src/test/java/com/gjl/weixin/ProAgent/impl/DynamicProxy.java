package com.gjl.weixin.ProAgent.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    //生命委托类对象
    private Object obj;

    public DynamicProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("bedore()");
        Object result = method.invoke(obj, args);
        System.out.println("after()");
        return result;
    }
}
