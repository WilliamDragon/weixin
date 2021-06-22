package com.gjl.weixin.java8.functionalexpression;

/**
 * @Author WilliamDragon
 * @Date 2021/6/22 10:48
 * @Version 1.0
 */

public class Testfunction {
    public static void main(String[] args) {
        ExpressFun expressFun = new ExpressFun();
        //（1）expressClearFun方法
        //java8 之前写法， 和new Thread(new Runable()) 写法一样
        expressFun.expressClearFun(new ClearFun() {
            @Override
            public void execute() {
                System.out.println("执行清理操作");
            }
        });
        //java8 之后写法， 直接用lamdba写
        expressFun.expressClearFun(()-> System.out.println("执行清理操作"));

        //（2）ExpressFunGetFun方法
        //java8 之后写法
        String str = "WilliamDragon";
        String s = expressFun.ExpressFunGetFun(str, (x) -> {
            String ss = "success";
            ss = ss + "->" + x;
            return ss;
        });
        System.out.println(s);
        String s1 = expressFun.ExpressFunGetFun(str, Testfunction::replcelamdba);
        System.out.println(s1);
    }

    public static String replcelamdba(String str){
        String ss = "success";
        ss = ss + "->" + str;
        return ss;
    }
}
