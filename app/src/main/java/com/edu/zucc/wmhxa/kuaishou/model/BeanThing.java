package com.edu.zucc.wmhxa.kuaishou.model;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BeanThing implements Serializable {
    //物品名称
    private String name;
    //预算
    private Double money;
    //数量 (选填)
    private int number;
    //购买地点定位
    private String address;
    //购买地点经纬度 (手动添加的地点没有这两个属性)
    private double longitude;
    private double latitude;

    //地点对象
    private LatLng xy = null;

    public BeanThing(String name, Double money, int number, String address, double longitude, double latitude) {
        this.name = name;
        this.money = money;
        this.number = number;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        xy = new LatLng(latitude, longitude);
    }

    public BeanThing(String name, String address, Double money, int number) {
        this.name = name;
        this.money = money;
        this.number = number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LatLng getXy() {
        return xy;
    }

    public void setXy(LatLng xy) {
        this.xy = xy;
    }
}
