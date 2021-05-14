package com.gjl.weixin.prototypePattern;

/**
 * @Author WilliamDragon
 * @Date 2021/4/23 13:58
 * @Version 1.0
 */

public class PrototypePatternModel1 implements Cloneable{

    private String name;
    private String age;

    public PrototypePatternModel1(){
        System.out.println("使用构造方法来创建得PrototypePatternModel1实例");
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("使用clone创建实例");
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


