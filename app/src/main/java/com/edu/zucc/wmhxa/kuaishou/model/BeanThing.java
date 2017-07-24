package com.edu.zucc.wmhxa.kuaishou.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BeanThing implements Serializable {
    private String name;
    private String address;
    private Double money;
    private int number;

    public BeanThing(String name, String address, Double money, int number) {
        this.name = name;
        this.address = address;
        this.money = money;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
