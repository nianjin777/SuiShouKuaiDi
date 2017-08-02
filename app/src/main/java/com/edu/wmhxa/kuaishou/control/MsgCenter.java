package com.edu.wmhxa.kuaishou.control;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.edu.wmhxa.kuaishou.model.BeanThing;
import com.edu.wmhxa.kuaishou.model.BeanUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private HttpControl httpControl = new HttpControl();

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
//        beanUser = new BeanUser("admin", "admin", "123456", "管理员", "350702199705301818", "17774009906", true, "404290080@qq.com");
        beanUser = null;
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
     * 所有的HTTP请求都在一下部分实现数据更新
     */

    public boolean login(String userName, String password) {
        //登陆 封装成json
        String loginURL = "login";
        JSONObject info = new JSONObject();
        BeanUser loginUser;
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", userName);
            info.put("userpassword", password);
            Log.i(TAG, info.toString());
//            JSONObject result = httpControl.postMethod(info, loginURL);//登陆的URL
//            Log.i(TAG, result.toString());
//            String error = (String) result.get("error");
//            if (error == null || error.isEmpty()) {
////                登陆成功 把信息封装成bean对象
//                loginUser = new BeanUser();
//                loginUser.setUserID(result.getInt("userid"));
//                loginUser.setName(result.getString("username"));
//                loginUser.setSex(result.getString("usersex"));
//                loginUser.setID(result.getString("useridcard"));
//                loginUser.setPhone(result.getString("userphone"));
//                loginUser.setEmail(result.getString("usermail"));
//                loginUser.setGood(result.getInt("usergood"));
//                beanUser = loginUser;
//            } else {
//                return false;
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean regist(BeanUser regUser) {
        String registURL = "regist";
        //封装json对象
        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("check", "remeber_client");
            userInfo.put("username", regUser.getName());
            userInfo.put("usersex", regUser.getSex());
            userInfo.put("useridcard", regUser.getID());
            userInfo.put("userphone", regUser.getPhone());
            userInfo.put("usermail", regUser.getEmail());
            userInfo.put("useraccount", regUser.getUsername());
            userInfo.put("userpassword", regUser.getPassword());
            Log.i(TAG, userInfo.toString());
//            JSONObject result = httpControl.postMethod(userInfo, registURL);
//            String error = (String) result.get("error");
//            if (error == null || error.isEmpty()) {
//                beanUser = regUser;
//                beanUser.setUserID(result.getInt("userid"));
//            } else {
//                return false;
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean getNearTask(LatLng position) {
        //获取当前位置下的订单
        String URL = "getneartask";

        double latitude = position.latitude;
        double longitude = position.longitude;
        String useraccount = beanUser.getUsername();
        //把数据封装成JSON
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("check", "remeber_client");
            jsonObject.put("useraccount", useraccount);
            jsonObject.put("userlongitude", longitude);
            jsonObject.put("userlatitude", latitude);
            JSONObject result = httpControl.postMethod(jsonObject, URL);
            String error = (String) result.get("error");
            if (error == null || error.isEmpty()) {
                JSONArray orderList = result.getJSONArray("order");
                List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < orderList.length(); i++) {
                    JSONObject orderInfo = orderList.getJSONObject(i);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("orderid", orderInfo.get("orderid"));
                    map.put("ordername", orderInfo.get("ordername"));
                    map.put("ordertext", orderInfo.get("ordertext"));
                    map.put("ordermoney", orderInfo.get("ordermoney"));
                    map.put("orderbounty", orderInfo.get("orderbounty"));
                    //封装每个物品
                    JSONArray things = orderInfo.getJSONArray("thing");
                    List<BeanThing> thingList = new ArrayList<BeanThing>();
                    for (int j = 0; j < things.length(); j++) {
                        JSONObject thingObject = things.getJSONObject(j);
                        BeanThing beanThing = new BeanThing();
                        beanThing.setThingid(thingObject.getInt("thingid"));
                        beanThing.setName(thingObject.getString("thingname"));
                        beanThing.setLongitude(thingObject.getDouble("thinglongitude"));
                        beanThing.setLatitude(thingObject.getDouble("thinglatitude"));
                        beanThing.setAddress(thingObject.getString("thinglocation"));
                        beanThing.setNumber(thingObject.getInt("thingnum"));
                        beanThing.setMoney(thingObject.getDouble("thingmoney"));
                        thingList.add(beanThing);
                    }
                    map.put("thing", thingList);
                    mapList.add(map);
                }
                nearTaskList = mapList;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean getAddrList() {


        return false;
    }

}