package com.edu.wmhxa.sskd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class BeanOrder implements Serializable {
    private int orderId;
    private BeanUser bossAccount;
    private BeanUser empAccount;
    private Calendar startTime;
    private Calendar endTime;
    private double money;
    private double bounty;
    private String evalBoss;
    private String evalEmp;
    private String orderName;
    private String orderText;
    private List<BeanThing> thingList = new ArrayList<BeanThing>();
    private BeanAddress address;
    private double distence;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BeanUser getEmpAccount() {
        return empAccount;
    }

    public void setEmpAccount(BeanUser empAccount) {
        this.empAccount = empAccount;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }

    public String getEvalBoss() {
        return evalBoss;
    }

    public void setEvalBoss(String evalBoss) {
        this.evalBoss = evalBoss;
    }

    public String getEvalEmp() {
        return evalEmp;
    }

    public void setEvalEmp(String evalEmp) {
        this.evalEmp = evalEmp;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public List<BeanThing> getThingList() {
        return thingList;
    }

    public void setThingList(List<BeanThing> thingList) {
        this.thingList = thingList;
    }

    public BeanAddress getAddress() {
        return address;
    }

    public void setAddress(BeanAddress address) {
        this.address = address;
    }

    public BeanUser getBossAccount() {
        return bossAccount;
    }

    public void setBossAccount(BeanUser bossAccount) {
        this.bossAccount = bossAccount;
    }

    public double getDistence() {
        return distence;
    }

    public void setDistence(double distence) {
        this.distence = distence;
    }

    @Override
    public String toString() {
        return "BeanOrder{" +
                "orderId=" + orderId +
                ", bossAccount='" + bossAccount + '\'' +
                ", empAccount='" + empAccount + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", money=" + money +
                ", bounty=" + bounty +
                ", evalBoss='" + evalBoss + '\'' +
                ", evalEmp='" + evalEmp + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderText='" + orderText + '\'' +
                ", thingList=" + thingList +
                ", address=" + address +
                ", distence=" + distence +
                '}';
    }
}
