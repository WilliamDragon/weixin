package com.gjl.weixin.java8.functionalexpression;

/**
 * @Author WilliamDragon
 * @Date 2021/6/22 10:36
 * @Version 1.0
 */
//调用所有函数式接口统一入口
public class ExpressFun {

    //调用ClearFun函数式接口
    public void expressClearFun(ClearFun  clearFun){
        clearFun.execute();
    }

    //调用getFun 函数式接口
    public String ExpressFunGetFun(String str,GetFun<String,String> getFun){
        return getFun.getExecute(str);
    }
}
