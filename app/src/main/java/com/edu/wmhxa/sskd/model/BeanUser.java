package com.edu.wmhxa.sskd.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/19.
 */

public class BeanUser implements Serializable {
    //TODO
//    public static BeanUser localUser = new BeanUser();
//    public static BeanUser localUser = null ;

    //    private int userID;//用户编号
    private String username;//帐号
    private String password;
    private String payPassword;
    private String name;//姓名
    private String ID;//身份证号码
    private String phone;//联系电话
    private boolean isCourier = false;
    private String email;//邮箱
    private String sex;
    private int good;

    public BeanUser(String username, String password, String payPassword,
                    String name, String ID, String phone, boolean isCourier, String email, String sex) {
        this.username = username;
        this.password = password;
        this.payPassword = payPassword;
        this.name = name;
        this.ID = ID;
        this.phone = phone;
        this.isCourier = isCourier;
        this.email = email;
        this.sex = sex;
    }

    public BeanUser() {
        super();
    }

//    public int getUserID() {
//        return userID;
//    }
//
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    @Override
    public String toString() {
        return "BeanUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", name='" + name + '\'' +
                ", ID='" + ID + '\'' +
                ", phone='" + phone + '\'' +
                ", isCourier=" + isCourier +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", good=" + good +
                '}';
    }
}
