package com.gjl.weixin.prototypePattern;

import org.junit.Before;

import java.io.Serializable;

/**
 * @Author WilliamDragon
 * @Date 2021/4/23 13:58
 * @Version 1.0
 * @Param  实现cloneable 是浅克隆， 深克隆可通过实现Serializable接口实现，详见ThreadPoolInfo
 */
class Model3 implements Cloneable{
    int heigth;//身高

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class PrototypePatternModel3 implements Cloneable{

    private String name;
    private String age;
    private Model3 model3;

    public PrototypePatternModel3(){
        this.model3 = new Model3();
    }

    public Model3 getModel3() {
        return model3;
    }


    public void setModel3(Model3 model3) {
        this.model3 = model3;
    }

    @Override
    protected PrototypePatternModel3 clone() throws CloneNotSupportedException {
        PrototypePatternModel3 prototypePatternModel3 = (PrototypePatternModel3)super.clone();
        prototypePatternModel3.setModel3((Model3)prototypePatternModel3.getModel3().clone());
        return prototypePatternModel3;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


