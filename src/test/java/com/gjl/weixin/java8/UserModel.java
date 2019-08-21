package com.gjl.weixin.java8;

import java.util.Date;

public class UserModel {

    private int id;
    private String name;
    private Date date;
    private String number;
    private int sex;
    private int ss;

    public UserModel(int id, String name, Date date, String number, int sex) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.number = number;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", number='" + number + '\'' +
                ", sex=" + sex +
                '}';
    }
}
