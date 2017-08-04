package com.edu.wmhxa.sskd.control;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.model.BeanUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by KarlCyan on 2017/7/25.
 * <p>
 * 消息中心，服务器获取到的数据都保存在这个类中
 */

public class MsgCenter {
    private static final String TAG = "MsgCenter";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private HttpControl httpControl = new HttpControl();

    //当前登陆用户
    public static BeanUser beanUser = null;
    //MsgCenter 单例
    public static MsgCenter msgCenter = null;

    /**
     * 附近任务表
     * 用户获取到位置后会向服务器拉取附近所有任务的任务信息存在List中
     */
    public static List<BeanOrder> nearTaskList = new ArrayList<BeanOrder>();
    public static List<BeanOrder> myOrderList = new ArrayList<BeanOrder>();
    public static List<BeanOrder> ingOrderList = new ArrayList<BeanOrder>();
    public static List<BeanOrder> waitOrderList = new ArrayList<BeanOrder>();
    public static List<BeanOrder> finishOrderList = new ArrayList<BeanOrder>();
    public static List<BeanOrder> empOrderList = new ArrayList<BeanOrder>();
    //订单详情
    public static Map<String, Object> orderInfo = null;
    //物品清单
    public static List<BeanThing> thingList = null;
    //朋友表
    public static List<BeanUser> friendList = null;
    //好友申请表
    public static List<BeanUser> applyFriendList = null;
    //地址列表
    public static List<BeanAddress> addressList = null;

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
        beanUser = new BeanUser("admin", "admin", "123456", "陈幼安", "350702199705301818", "17774009906", true, "404290080@qq.com", "男");
//        beanUser = null;
        //模拟附近订单
        nearTaskList = new ArrayList<BeanOrder>();
        for (int i = 0; i < 10; i++) {
            BeanOrder order = new BeanOrder();
            order.setBossAccount("luren" + i);
            order.setOrderName("任务" + i);
            order.setOrderText("备注" + i);
            order.setMoney(10.0 + i);
            order.setBounty(1.0 + i);
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
            order.setThingList(thingList);
            BeanAddress beanAddress = new BeanAddress();
            beanAddress.setLocation("地点" + i);
            order.setAddress(beanAddress);

            nearTaskList.add(order);
            Log.i(TAG, "添加一个订单");
        }

        friendList = new ArrayList<BeanUser>();
        for (int i = 0; i < 20; i++) {
            BeanUser beanUser = new BeanUser();
            beanUser.setName("朋友" + i);
            beanUser.setSex("女");
            beanUser.setPhone("110" + i);
            beanUser.setUsername("userAccount" + i);
            beanUser.setGood(i);
            friendList.add(beanUser);
        }

        applyFriendList = new ArrayList<BeanUser>();
        BeanUser addUser = new BeanUser();
        addUser.setName("申请人1");
        addUser.setSex("男");
        addUser.setGood(100);
        applyFriendList.add(addUser);

        //地址
        addressList = new ArrayList<BeanAddress>();
        for (int i = 0; i < 6; i++) {
            BeanAddress beanAddress = new BeanAddress();
            beanAddress.setName("收货人" + i);
            beanAddress.setPhone("110" + i);
            beanAddress.setLocation("定位" + i);
            beanAddress.setInfo("详细地址" + i);
            beanAddress.setAddrId(i);
            if (i == 3) {
                beanAddress.setAddrDefault(true);
                BeanAddress.indexDeault = i;
            } else {
                beanAddress.setAddrDefault(false);
            }
            addressList.add(beanAddress);
        }

    }

    /**
     * 功能模块
     * 所有的HTTP请求都在一下部分实现数据更新
     */

    //用户操作
    //登陆
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
            JSONObject result = httpControl.postMethod(info, loginURL);//登陆的URL
            if (result == null) {
                return false;
            }
            Log.i(TAG, result.toString());
            String error = (String) result.get("error");
            if (error == null || error.isEmpty()) {
//                登陆成功 把信息封装成bean对象
                loginUser = new BeanUser();
                loginUser.setName(result.getString("username"));
                loginUser.setSex(result.getString("usersex"));
                loginUser.setID(result.getString("useridcard"));
                loginUser.setPhone(result.getString("userphone"));
                loginUser.setEmail(result.getString("usermail"));
                loginUser.setGood(result.getInt("usergood"));
                beanUser = loginUser;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //注册
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
            JSONObject result = httpControl.postMethod(userInfo, registURL);
            if (result == null) {
                return false;
            }
            String error = (String) result.get("error");
            if (error == null || error.isEmpty()) {
                beanUser = regUser;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //获取好友列表
    public boolean getFriendList() {
        String URL = "regist";
        //封装json对象
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());

            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                JSONArray friendlist = result.getJSONArray("friendlist");
                List<BeanUser> list = new ArrayList<BeanUser>();
                for (int i = 0; i < friendlist.length(); i++) {
                    JSONObject jsonObject = friendlist.getJSONObject(i);
                    BeanUser beanUser = new BeanUser();
                    beanUser.setName(jsonObject.getString("username"));
                    beanUser.setSex(jsonObject.getString("usersex"));
                    beanUser.setPhone(jsonObject.getString("userphone"));
                    beanUser.setUsername(jsonObject.getString("useraccount"));
                    beanUser.setGood(jsonObject.getInt("usergood"));
                    list.add(beanUser);
                }
                friendList = list;
            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    //好友申请
    public boolean addFriend(String friendAccount) {
        String URL = "addFriend";
        //封装json对象
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("friendaccount", friendAccount);
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                //返回正确
            } else {
                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //搜索用户 通过用户帐号搜索
    public BeanUser searchUser(String userAccount) {
        String URL = "searchUser";
        BeanUser beanUser = null;
        //封装json对象
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("username", userAccount);
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return null;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                //返回正确
                beanUser = new BeanUser();
                beanUser.setName(result.getString("username"));
                beanUser.setSex(result.getString("usersex"));
                beanUser.setID(result.getString("useridcard"));
                beanUser.setPhone(result.getString("userphone"));
                beanUser.setEmail(result.getString("usermail"));
                beanUser.setUsername(result.getString("useraccount"));
            } else {
                return null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return beanUser;
    }

    //修改手机
//    public boolean changePhone(String phone) {
//        String URL = "changePhone";
//        //封装json对象
//        JSONObject info = new JSONObject();
//        try {
//            info.put("check", "remeber_client");
//            info.put("useraccount", beanUser.getUsername());
//            info.put("userphone", phone);
//            JSONObject result = httpControl.postMethod(info, URL);
//            if (result == null) {
//                return false;
//            }
//            String error = result.getString("error");
//            if (error == null || error.isEmpty()) {
//            } else {
//                return false;
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        return true;
//    }

    //修改手机
    public boolean changeEmailAndPhone(String email, String phone) {
        String URL = "changeEmail";
        //封装json对象
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("usermail", email);
            info.put("userphone", phone);
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //地址操作
    //获取地址列表
    public boolean getAddrList() {

        String URL = "getAddrList";
        //封装json对象
        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("check", "remeber_client");
            userInfo.put("useraccount", beanUser.getUsername());
            JSONObject result = httpControl.postMethod(userInfo, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                JSONArray addresslist = result.getJSONArray("addresslist");
                List<BeanAddress> list = new ArrayList<BeanAddress>();
                for (int i = 0; i < addresslist.length(); i++) {
                    JSONObject addr = addresslist.getJSONObject(i);
                    BeanAddress beanAddress = new BeanAddress();
                    beanAddress.setAddrId(addr.getInt("addrid"));
                    beanAddress.setLocation(addr.getString("addrlocation"));
                    beanAddress.setInfo(addr.getString("addrinfo"));
                    beanAddress.setName(addr.getString("addruser"));
                    beanAddress.setPhone(addr.getString("addrphone"));
                    beanAddress.setAddrDefault(addr.getBoolean("addrdefault"));
                    list.add(beanAddress);
                }
                addressList = list;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    //新增地址
    public boolean addAddress(BeanAddress beanAddress) {
        String URL = "addAddress";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("addrlocation", beanAddress.getLocation());
            info.put("addrinfo", beanAddress.getInfo());
            info.put("addruser", beanAddress.getName());
            info.put("addrphone", beanAddress.getPhone());
            info.put("addrdefault", beanAddress.isAddrDefault());
            Log.i(TAG, info.toString());

            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            int addrid = result.getInt("addrid");
            if (addrid == -1) {
                return false;
            } else {
                beanAddress.setAddrId(addrid);
                addressList.add(beanAddress);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //删除地址
    public boolean deleteAddress(int addrId) {
        String URL = "deleteAddress";
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("addrid", addrId);
            Log.i(TAG, info.toString());
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                for (int i = 0; i < addressList.size(); i++) {
                    if (addrId == addressList.get(i).getAddrId()) {
                        addressList.remove(i);
                    }
                }
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    //修改地址
    public boolean changeAddress(BeanAddress beanAddress) {
        String URL = "changeAddress";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("addrlocation", beanAddress.getLocation());
            info.put("addrinfo", beanAddress.getInfo());
            info.put("addruser", beanAddress.getName());
            info.put("addrphone", beanAddress.getPhone());

            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //修改默认地址
    public boolean changeDefaultAddress(int addrId) {
        String URL = "changeDefaultAddress";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("addrid", addrId);

            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //订单操作
    //获取位置周边订单信息
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
            JSONObject result = httpControl.postMethod(jsonObject, URL);//拿到数据
            if (result == null) {
                return false;
            }
            if (result == null) {
                return false;
            }
            String error = (String) result.get("error");
            if (error == null || error.isEmpty()) {
                JSONArray order = result.getJSONArray("order");
                List<BeanOrder> orderList = new ArrayList<BeanOrder>();
                for (int i = 0; i < order.length(); i++) {
                    JSONObject orderInfo = order.getJSONObject(i);
                    BeanOrder beanOrder = new BeanOrder();
                    beanOrder.setOrderId(orderInfo.getInt("orderid"));
                    beanOrder.setOrderName(orderInfo.getString("ordername"));
                    beanOrder.setOrderText(orderInfo.getString("ordertext"));
                    beanOrder.setMoney(orderInfo.getDouble("ordermoney"));
                    beanOrder.setBounty(orderInfo.getDouble("orderbounty"));
                    //封装物品表
                    JSONArray thing = orderInfo.getJSONArray("thing");
                    List<BeanThing> thingList = new ArrayList<BeanThing>();
                    for (int j = 0; j < thing.length(); j++) {
                        JSONObject thingInfo = thing.getJSONObject(j);
                        BeanThing beanThing = new BeanThing();
                        beanThing.setName(thingInfo.getString("thingname"));
                        beanThing.setLongitude(thingInfo.getDouble("thinglongitude"));
                        beanThing.setLatitude(thingInfo.getDouble("thinglatitude"));
                        beanThing.setAddress(thingInfo.getString("thinglocation"));
                        beanThing.setNumber(thingInfo.getInt("thingnum"));
                        beanThing.setMoney(thingInfo.getDouble("thingmoney"));
                        thingList.add(beanThing);
                    }
                    beanOrder.setThingList(thingList);
                    BeanAddress beanAddress = new BeanAddress();
                    beanAddress.setLocation(orderInfo.getString("addrlocation"));
                    beanOrder.setAddress(beanAddress);
                    orderList.add(beanOrder);
                }
                nearTaskList = orderList;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //获取我的订单(包括所有状态)
    public boolean getMyOrderList() {
        //获取当前位置下的订单
        String URL = "getMyOrderList";
        String useraccount = beanUser.getUsername();
        //把数据封装成JSON
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("check", "remeber_client");
            jsonObject.put("useraccount", useraccount);
            JSONObject result = httpControl.postMethod(jsonObject, URL);//拿到数据
            if (result == null) {
                return false;
            }
            String error = (String) result.get("error");
            if (error == null || error.isEmpty()) {
                JSONArray order = result.getJSONArray("order");
                List<BeanOrder> orderList = new ArrayList<BeanOrder>();
                for (int i = 0; i < order.length(); i++) {
                    JSONObject orderInfo = order.getJSONObject(i);
                    BeanOrder beanOrder = new BeanOrder();
                    beanOrder.setOrderId(orderInfo.getInt("orderid"));
                    beanOrder.setEmpAccount(orderInfo.getString("orderempaccount"));

                    Calendar startTime = Calendar.getInstance();
                    startTime.setTime(format.parse(orderInfo.getString("orderstarttime")));
                    beanOrder.setStartTime(startTime);
                    Calendar endTime = Calendar.getInstance();
                    endTime.setTime(format.parse(orderInfo.getString("oederendtime")));
                    beanOrder.setEndTime(endTime);

                    beanOrder.setEvalBoss(orderInfo.getString("orderevalboss"));
                    beanOrder.setEvalEmp(orderInfo.getString("orderevalemp"));
                    beanOrder.setOrderName(orderInfo.getString("ordername"));
                    beanOrder.setOrderText(orderInfo.getString("ordertext"));
                    beanOrder.setMoney(orderInfo.getDouble("ordermoney"));
                    beanOrder.setBounty(orderInfo.getDouble("orderbounty"));
                    //封装物品表
                    JSONArray thing = orderInfo.getJSONArray("thing");
                    List<BeanThing> thingList = new ArrayList<BeanThing>();
                    for (int j = 0; j < thing.length(); j++) {
                        JSONObject thingInfo = thing.getJSONObject(j);
                        BeanThing beanThing = new BeanThing();
                        beanThing.setName(thingInfo.getString("thingname"));
                        beanThing.setLongitude(thingInfo.getDouble("thinglongitude"));
                        beanThing.setLatitude(thingInfo.getDouble("thinglatitude"));
                        beanThing.setAddress(thingInfo.getString("thinglocation"));
                        beanThing.setNumber(thingInfo.getInt("thingnum"));
                        beanThing.setMoney(thingInfo.getDouble("thingmoney"));
                        thingList.add(beanThing);
                    }
                    beanOrder.setThingList(thingList);
                    int addrid = orderInfo.getInt("addrid");
                    for (int index = 0; index < addressList.size(); index++) {
                        if (addressList.get(index).getAddrId() == addrid) {
                            beanOrder.setAddress(addressList.get(index));
                        }
                    }
                    if (beanOrder.getAddress() == null) {
                        Log.i(TAG, "getMyOrderList:返回的地址id不存在");
                        beanOrder.setAddress(new BeanAddress());
                    }
                    orderList.add(beanOrder);
                }
                myOrderList = orderList;
                sortOrderList();
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void sortOrderList() {
        ingOrderList = new ArrayList<BeanOrder>();
        finishOrderList = new ArrayList<BeanOrder>();
        waitOrderList = new ArrayList<BeanOrder>();
        empOrderList = new ArrayList<BeanOrder>();

        for (int i = 0; i < myOrderList.size(); i++) {
            BeanOrder beanOrder = myOrderList.get(i);
            if (beanOrder.getStartTime() == null) {
                //没有开始时间 是等待接单 一定是自己的
                waitOrderList.add(beanOrder);
                continue;
            } else if (beanOrder.getStartTime() != null && beanOrder.getEndTime() == null) {
                //有开始时间 没有结束时间 正在进行 有可能是当前用户自己的,也有可能是他在快递的
                if (beanOrder.getEmpAccount() == beanUser.getUsername()) {
                    //是自己在负责的
                    empOrderList.add(beanOrder);
                } else {
                    //如果不是自己负责的 那就是自己的
                    ingOrderList.add(beanOrder);
                }
                continue;
            } else if (beanOrder.getStartTime() != null && beanOrder.getEndTime() != null) {
                //开始和结束时间都有了 那就是已经结束的订单 不考虑有没评价过
                finishOrderList.add(beanOrder);
            }
        }
    }

    //新增订单
    public int addOrder(BeanOrder order) {
        int orderid = -1;
        String URL = "addOrder";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("ordername", order.getOrderName());
            info.put("ordertext", order.getOrderText());
            info.put("ordermoney", order.getMoney());
            info.put("orderbounty", order.getBounty());
            JSONArray thing = new JSONArray();
            List<BeanThing> thingList = order.getThingList();
            for (int i = 0; i < thingList.size(); i++) {
                JSONObject thingInfo = new JSONObject();
                BeanThing beanThing = thingList.get(i);
                thingInfo.put("thingname", beanThing.getName());
                thingInfo.put("thinglongitude", beanThing.getLongitude());
                thingInfo.put("thinglatitude", beanThing.getLatitude());
                thingInfo.put("thinglocation", beanThing.getAddress());
                thingInfo.put("thingnum", beanThing.getNumber());
                thingInfo.put("thingmoney", beanThing.getMoney());
                thing.put(thingInfo);
            }
            info.put("thing", thing);
            info.put("addrid", order.getAddress().getAddrId());
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return -1;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                orderid = result.getInt("orderid");
                order.setOrderId(orderid);
                myOrderList.add(order);
            } else {
                return -1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderid;
    }

    //修改订单
    public int changeOrder(BeanOrder order) {
        int orderid = -1;
        String URL = "changeOrder";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("ordername", order.getOrderName());
            info.put("ordertext", order.getOrderText());
            info.put("ordermoney", order.getMoney());
            info.put("orderbounty", order.getBounty());
            JSONArray thing = new JSONArray();
            List<BeanThing> thingList = order.getThingList();
            for (int i = 0; i < thingList.size(); i++) {
                JSONObject thingInfo = new JSONObject();
                BeanThing beanThing = thingList.get(i);
                thingInfo.put("thingname", beanThing.getName());
                thingInfo.put("thinglongitude", beanThing.getLongitude());
                thingInfo.put("thinglatitude", beanThing.getLatitude());
                thingInfo.put("thinglocation", beanThing.getAddress());
                thingInfo.put("thingnum", beanThing.getNumber());
                thingInfo.put("thingmoney", beanThing.getMoney());
                thing.put(thingInfo);
            }
            info.put("thing", thing);
            info.put("addrid", order.getAddress().getAddrId());
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return -1;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                orderid = result.getInt("orderid");
            } else {
                return orderid = -1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return orderid;
    }

    //接受订单
    public BeanAddress acceptOrder(BeanOrder order) {
        BeanAddress beanAddress = null;
        //获取当前位置下的订单
        String URL = "acceptOrder";
        String useraccount = beanUser.getUsername();
        //把数据封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", useraccount);
            info.put("orderid", order.getOrderId());
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return null;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                beanAddress = new BeanAddress();
                beanAddress.setLocation(result.getString("addrlocation"));
                beanAddress.setInfo(result.getString("addrinfo"));
                beanAddress.setName(result.getString("addruser"));
                beanAddress.setPhone(result.getString("addrphone"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beanAddress;
    }

    //完成订单
    public Calendar completeOrder(BeanOrder order) {
        Calendar instance = Calendar.getInstance();
        String URL = "completeOrder";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("orderid", order.getOrderId());
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return null;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                String orderendtime = result.getString("orderendtime");
                instance.setTime(format.parse(orderendtime));
            } else {
                instance = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return instance;
    }

    //取消订单

    //评价
    public boolean eval(BeanOrder order, String eval) {
        String URL = "evl";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("orderid", order.getOrderId());
            info.put("evalaccount", order.getEmpAccount());
            info.put("text", eval);
            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

}