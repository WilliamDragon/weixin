package com.gjl.weixin.java8.functionalexpression;

/**
 * @Author WilliamDragon
 * @Date 2021/6/22 10:33
 * @Version 1.0
 */
//取值功能，函数式接口
@FunctionalInterface
public interface GetFun<T,R> {

    public R getExecute(T t);
}
