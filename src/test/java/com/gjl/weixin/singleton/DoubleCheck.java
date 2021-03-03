package com.gjl.weixin.singleton;

public class DoubleCheck {

    private static volatile DoubleCheck instance;
    public DoubleCheck(){};
    public static DoubleCheck getInstance(){
        if(instance == null){
            synchronized (DoubleCheck.class){
                if(instance == null){
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }

}
