package com.gjl.weixin.ProAgent.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//动态代理 此类相当于是代理类与委托类之间的中间类
public interface InvocationHandler {

    Object invoke(Object proxy, Method method, Object[] aargs) throws InvocationTargetException, IllegalAccessException;

}
