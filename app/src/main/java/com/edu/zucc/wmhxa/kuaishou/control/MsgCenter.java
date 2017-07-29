package com.edu.zucc.wmhxa.kuaishou.control;

import android.util.Log;

import com.edu.zucc.wmhxa.kuaishou.model.BeanThing;
import com.edu.zucc.wmhxa.kuaishou.model.BeanUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KarlCyan on 2017/7/25.
 * <p>
 * 消息中心，服务器获取到的数据都保存在这个类中
 */

public class MsgCenter {
    private static final String TAG = "MsgCenter";

    //当前登陆用户
    public static BeanUser beanUser = null;
    //MsgCenter 单例
    public static MsgCenter msgCenter = null;

    /**
     * 附近任务表
     * 用户获取到位置后会向服务器拉取附近所有任务的任务信息存在List中
     */
    public static List<Map<String, Object>> nearTaskList = null;
    //订单详情
    public static Map<String, Object> orderInfo = null;
    //物品清单
    public static List<BeanThing> thingList = null;
    //朋友表
    public static List<BeanUser> friendList = null;

    private MsgCenter() {
        initData();
    }

    public static MsgCenter getInstanceMsgCenter() {
        if (msgCenter == null) {
            msgCenter = new MsgCenter();
        }
        return msgCenter;
    }

    private static void initData() {
        //模拟当前登陆用户
        beanUser = new BeanUser("admin", "admin", "123456", "管理员", "350702199705301818", "17774009906", true, "404290080@qq.com");
        //模拟附近订单
        nearTaskList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> order = new HashMap<String, Object>();
            order.put("orderboss", "luren" + i);
            order.put("ordername", "任务" + i);
            order.put("ordertext", "备注" + i);
            order.put("ordermoney", 10.0 + i);
            order.put("orderbounty", 1.0 + i);
            List<BeanThing> thingList = new ArrayList<BeanThing>();
            for (int j = 0; j < 5; j++) {
                BeanThing beanThing = new BeanThing();
                beanThing.setName("物品" + (j + 1));
                beanThing.setNumber(5);
                beanThing.setMoney(5.0 + i);
                beanThing.setLatitude(30.3208407389);
                beanThing.setLongitude(120.1413774490);
                beanThing.setAddress("源清中学" + i);

                thingList.add(beanThing);
                Log.i(TAG, "添加一个物品");
            }
            order.put("thinglist", thingList);
            nearTaskList.add(order);
            Log.i(TAG, "添加一个订单");
        }

        friendList = new ArrayList<BeanUser>();
        for (int i = 0; i < 20; i++) {
            BeanUser beanUser = new BeanUser();
            beanUser.setUserID(i);
            beanUser.setName("朋友" + i);
            beanUser.setSex("女");
            beanUser.setPhone("110" + i);
            beanUser.setUsername("userAccount" + i);
            beanUser.setGood(i);
            friendList.add(beanUser);
        }

    }

    /**
     * 功能模块
     *      所有的HTTP请求都在一下部分实现数据更新
     */

}