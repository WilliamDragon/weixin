package com.gjl.weixin.common.base;

import java.io.Serializable;

/**
 * DTO,BO,PO的父类
 * guojinlong
 * 2021/1/7
 * 14:50
 */
public class BaseObject implements Serializable {

    private static final long serialVersionUID = 8555375606571835479L;

    //todo 主要实现toString的JOSN格式输出
    public String toString(){
        return "todo";
    }
}
