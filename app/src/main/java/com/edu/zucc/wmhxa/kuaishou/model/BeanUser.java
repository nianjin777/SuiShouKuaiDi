package com.edu.zucc.wmhxa.kuaishou.model;

/**
 * Created by Administrator on 2017/7/19.
 */

public class BeanUser {
    //TODO
    public static BeanUser localUser = new BeanUser();
//    public static BeanUser localUser = null ;

    private String userID;//用户编号
    private String username;
    private String password;
    private String payPassword;
    private String name;//姓名
    private String ID;//身份证号码
    private String phone;//联系电话
    private boolean isCourier = false;
    private String email;//邮箱

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCourier() {
        return isCourier;
    }

    public void setCourier(boolean courier) {
        isCourier = courier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
