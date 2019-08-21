package com.gjl.weixin.java8;

public class UserTrade {
    private UserModel userModel;
    private String str1;
    private int int1;

    public UserTrade(UserModel userModel, String str1, int int1) {
        this.userModel = userModel;
        this.str1 = str1;
        this.int1 = int1;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }
}
