package com.edu.wmhxa.sskd.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */

public class BeanAddress implements Serializable {

    public static int indexDeault;

    private int addrId;
    private String location;
    private String info;
    private String name;
    private String phone;
    private boolean addrDefault;

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAddrDefault() {
        return addrDefault;
    }

    public void setAddrDefault(boolean addrDefault) {
        this.addrDefault = addrDefault;
    }

//    public static void addList(List<BeanAddress> addressList, BeanAddress address) {
//        int inDefault = 0;
//        for (int i = 0; i < addressList.size(); i++) {
//            if (addressList.get(i).isAddrDefault()) {
//                inDefault = i;
//            }
//        }
//    }


    @Override
    public String toString() {
        return "BeanAddress{" +
                "addrId=" + addrId +
                ", location='" + location + '\'' +
                ", info='" + info + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", addrDefault=" + addrDefault +
                '}';
    }
}
