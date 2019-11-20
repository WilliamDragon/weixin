package com.gjl.weixin.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @Author: WilliamJL
 * @Date: 2019/9/11 9:53
 * @Version 1.0
 */
@ExcelTarget("user")
public class User implements java.io.Serializable {

    @Excel(name = "userId")
    private Long userId;

    @Excel(name = "name")
    private String name;

    @Excel(name = "age")
    private Integer age;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
