package com.gjl.weixin.prototypePattern;

/**
 * @Author WilliamDragon
 * @Date 2021/4/23 13:58
 * @Version 1.0
 */
class Model2{
    int heigth;//身高
}
public class PrototypePatternModel2 implements Cloneable{

    private String name;
    private String age;
    private Model2 model2;

    public PrototypePatternModel2(){
        this.model2 = new Model2();
    }

    public Model2 getModel2() {
        return model2;
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


