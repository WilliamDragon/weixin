package com.gjl.weixin.prototypePattern;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author WilliamDragon
 * @Date 2021/4/23 14:05
 * @Version 1.0
 */

public class TestPrototypePattern {

    public static void main(String[] args) throws CloneNotSupportedException {

       /* PrototypePatternModel1 prototypePatternModel1 = new PrototypePatternModel1();
        System.out.println(prototypePatternModel1.clone());*/


        /*PrototypePatternModel2 cloneModel1 = new PrototypePatternModel2();
        PrototypePatternModel2 cloneModel2 = (PrototypePatternModel2)cloneModel1.clone();
        System.out.println(cloneModel1.getModel2()==cloneModel2.getModel2());*/

        /*PrototypePatternModel3 cloneModel3 = new PrototypePatternModel3();
        PrototypePatternModel3 cloneModel4 = (PrototypePatternModel3)cloneModel3.clone();
        System.out.println(cloneModel3.getModel3()==cloneModel4.getModel3());*/

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(20);

    }

}
