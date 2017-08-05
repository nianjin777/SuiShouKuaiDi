package com.edu.wmhxa.sskd.control;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.model.BeanUser;
import com.edu.wmhxa.sskd.util.ShowToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static android.R.attr.imeOptions;
import static android.R.attr.password;

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
    public static List<BeanThing> thingList = new ArrayList<BeanThing>();
    //朋友表
    public static List<BeanUser> friendList = new ArrayList<BeanUser>();
    //正在进行的订单对话
    public static List<BeanUser> orderMsgList = new ArrayList<BeanUser>();
    //好友申请表
    public static List<BeanUser> applyFriendList = new ArrayList<BeanUser>();
    //地址列表
    public static List<BeanAddress> addressList = new ArrayList<BeanAddress>();

    public static String errorInfo = "";

    private MsgCenter() {
//        initData();
    }

    public static MsgCenter getInstanceMsgCenter() {
        if (msgCenter == null) {
            msgCenter = new MsgCenter();
        }
        return msgCenter;
    }

    /**
     * 功能模块
     * 所有的HTTP请求都在一下部分实现数据更新
     */

    //用户操作
    //登陆
    public boolean login(String userName, String password) {
        errorInfo = "";
        //登陆 封装成json
        String loginURL = "user_login.action";
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
            Log.i(TAG, "result:" + result.toString());
            String error = result.getString("error");
            Log.i(TAG, "error:" + error);
            if (error == null || error.isEmpty()) {
//                登陆成功 把信息封装成bean对象
                JSONObject userJSONObject = result.getJSONObject("user");
                loginUser = new BeanUser();
                loginUser.setUsername(userName);
                loginUser.setName(userJSONObject.getString("userName"));
                loginUser.setSex(userJSONObject.getString("userSex"));
                loginUser.setID(userJSONObject.getString("userIdcard"));
                loginUser.setPhone(userJSONObject.getString("userPhone"));
                loginUser.setEmail(userJSONObject.getString("userEmail"));
                loginUser.setGood(userJSONObject.getInt("userGood"));
                beanUser = loginUser;
                Log.i(TAG, beanUser.toString());

                //封装好友列表
                JSONArray friends = userJSONObject.getJSONArray("friend");
                for (int i = 0; i < friends.length(); i++) {
                    JSONObject jsonObject = friends.getJSONObject(i);
                    JSONObject friend = jsonObject.getJSONObject("friend");
                    BeanUser beanUser = new BeanUser();
                    beanUser.setName(friend.getString("userName"));
                    beanUser.setSex(friend.getString("userSex"));
                    beanUser.setPhone(friend.getString("userPhone"));
                    beanUser.setUsername(friend.getString("userAccount"));
                    beanUser.setGood(friend.getInt("userGood"));
                    beanUser.setEmail(friend.getString("userEmail"));
                    friendList.add(beanUser);
                }

                //封装地址
                JSONArray addressJSONArray = userJSONObject.getJSONArray("address");
                for (int i = 0; i < addressJSONArray.length(); i++) {
                    JSONObject addr = addressJSONArray.getJSONObject(i);
                    BeanAddress beanAddress = new BeanAddress();
                    beanAddress.setAddrId(addr.getInt("addrId"));
                    beanAddress.setLocation(addr.getString("addrLocation"));
                    beanAddress.setInfo(addr.getString("addrInfo"));
                    beanAddress.setName(addr.getString("addrUser"));
                    beanAddress.setPhone(addr.getString("addrPhone"));
                    int addrDefault = addr.getInt("addrDefault");
                    if (addrDefault == 1) {
                        beanAddress.setAddrDefault(true);
                        BeanAddress.indexDeault = i;
                    } else {
                        beanAddress.setAddrDefault(false);
                    }
                    addressList.add(beanAddress);
                }

                //封装订单
                JSONArray orderJSONArray = userJSONObject.getJSONArray("order");
                for (int i = 0; i < orderJSONArray.length(); i++) {
                    JSONObject order = orderJSONArray.getJSONObject(i);
                    BeanOrder beanOrder = new BeanOrder();
                    beanOrder.setOrderId(order.getInt("orderId"));

                    String orderStartTime = order.getString("orderStartTime");
                    if (orderStartTime != null && !orderStartTime.isEmpty() && !orderStartTime.equals("null")) {
                        Calendar startTime = Calendar.getInstance();
                        startTime.setTime(format.parse(order.getString("orderStartTime")));
                        beanOrder.setStartTime(startTime);
                    }
                    String orderEndTime = order.getString("orderEndTime");
                    if (orderEndTime != null && !orderEndTime.isEmpty() && !orderEndTime.equals("null")) {
                        Calendar endTime = Calendar.getInstance();
                        endTime.setTime(format.parse(order.getString("orderEndTime")));
                        beanOrder.setEndTime(endTime);
                    }

                    beanOrder.setMoney(order.getDouble("orderMoney"));
                    beanOrder.setBounty(order.getDouble("orderBounty"));
                    beanOrder.setEvalBoss(order.getString("orderEvalBoss"));
                    beanOrder.setEvalEmp(order.getString("orderEvalEmp"));
                    beanOrder.setOrderName(order.getString("orderName"));
                    beanOrder.setOrderText(order.getString("orderText"));

                    //添加物品
                    JSONArray thingArray = order.getJSONArray("thing");
                    List<BeanThing> beanThings = new ArrayList<BeanThing>();
                    for (int j = 0; j < thingArray.length(); j++) {
                        JSONObject thing = thingArray.getJSONObject(j);
                        BeanThing beanThing = new BeanThing();
                        beanThing.setThingid(thing.getInt("thingId"));
                        beanThing.setName(thing.getString("thingName"));
                        beanThing.setLongitude(thing.getDouble("thingLongitude"));
                        beanThing.setLatitude(thing.getDouble("thingLatitude"));
                        beanThing.setAddress(thing.getString("thingLocation"));
                        beanThing.setNumber(thing.getInt("thingNum"));
                        beanThing.setMoney(thing.getDouble("thingMoney"));
                        beanThings.add(beanThing);
                    }
                    beanOrder.setThingList(beanThings);

                    //订单内的地址
                    JSONObject addressObject = order.getJSONObject("address");
                    BeanAddress beanAddress = new BeanAddress();
                    beanAddress.setAddrId(addressObject.getInt("addrId"));
                    beanAddress.setInfo(addressObject.getString("addrInfo"));
                    beanAddress.setLocation(addressObject.getString("addrLocation"));
                    beanAddress.setPhone(addressObject.getString("addrPhone"));
                    beanAddress.setName(addressObject.getString("addrUser"));
                    beanOrder.setAddress(beanAddress);
                    //快递员信息
                    if (!order.getString("orderEmp").equals("null")) {
                        BeanUser beanEmp = new BeanUser();
                        JSONObject orderEmp = order.getJSONObject("orderEmp");
                        beanEmp.setID(orderEmp.getString("userAccount"));
                        beanEmp.setEmail(orderEmp.getString("userEmail"));
                        beanEmp.setName(orderEmp.getString("userName"));
                        beanEmp.setGood(orderEmp.getInt("userGood"));
                        beanEmp.setSex(orderEmp.getString("userSex"));
                        beanOrder.setEmpAccount(beanEmp);
                    }

                    myOrderList.add(beanOrder);
                }
                //封装empOrder
                orderJSONArray = userJSONObject.getJSONArray("empOrder");
                for (int i = 0; i < orderJSONArray.length(); i++) {
                    JSONObject order = orderJSONArray.getJSONObject(i);
                    BeanOrder beanOrder = new BeanOrder();
                    beanOrder.setOrderId(order.getInt("orderId"));

                    String orderStartTime = order.getString("orderStartTime");
                    if (orderStartTime != null && !orderStartTime.isEmpty() && !orderStartTime.equals("null")) {
                        Calendar startTime = Calendar.getInstance();
                        startTime.setTime(format.parse(order.getString("orderStartTime")));
                        beanOrder.setStartTime(startTime);
                    }
                    String orderEndTime = order.getString("orderEndTime");
                    if (orderEndTime != null && !orderEndTime.isEmpty() && !orderEndTime.equals("null")) {
                        Calendar endTime = Calendar.getInstance();
                        endTime.setTime(format.parse(order.getString("orderEndTime")));
                        beanOrder.setEndTime(endTime);
                    }

                    beanOrder.setMoney(order.getDouble("orderMoney"));
                    beanOrder.setBounty(order.getDouble("orderBounty"));
                    beanOrder.setEvalBoss(order.getString("orderEvalBoss"));
                    beanOrder.setEvalEmp(order.getString("orderEvalEmp"));
                    beanOrder.setOrderName(order.getString("orderName"));
                    beanOrder.setOrderText(order.getString("orderText"));

                    //添加物品
                    JSONArray thingArray = order.getJSONArray("thing");
                    List<BeanThing> beanThings = new ArrayList<BeanThing>();
                    for (int j = 0; j < thingArray.length(); j++) {
                        JSONObject thing = thingArray.getJSONObject(j);
                        BeanThing beanThing = new BeanThing();
                        beanThing.setThingid(thing.getInt("thingId"));
                        beanThing.setName(thing.getString("thingName"));
                        beanThing.setLongitude(thing.getDouble("thingLongitude"));
                        beanThing.setLatitude(thing.getDouble("thingLatitude"));
                        beanThing.setAddress(thing.getString("thingLocation"));
                        beanThing.setNumber(thing.getInt("thingNum"));
                        beanThing.setMoney(thing.getDouble("thingMoney"));
                        beanThings.add(beanThing);
                    }
                    beanOrder.setThingList(beanThings);

                    //订单内的地址
                    JSONObject addressObject = order.getJSONObject("address");
                    BeanAddress beanAddress = new BeanAddress();
                    beanAddress.setAddrId(addressObject.getInt("addrId"));
                    beanAddress.setInfo(addressObject.getString("addrInfo"));
                    beanAddress.setLocation(addressObject.getString("addrLocation"));
                    beanAddress.setPhone(addressObject.getString("addrPhone"));
                    beanAddress.setName(addressObject.getString("addrUser"));
                    beanOrder.setAddress(beanAddress);
                    //快递员信息
                    beanOrder.setEmpAccount(beanUser);

                    myOrderList.add(beanOrder);
                }
                sortOrderList();

                Log.i(TAG, "登陆成功,返回true");
                return true;
            } else {
                errorInfo = error;
                Log.i(TAG, "登陆失败,返回false");
                return false;
            }
        } catch (JSONException e) {
            Log.i(TAG, e.toString());
        } catch (ParseException e) {
            Log.i(TAG, e.toString());
        }
        return false;
    }

    //注册
    public boolean regist(BeanUser regUser) {
        String registURL = "user_add.action";
        errorInfo = "";
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
            Log.i(TAG, result.toString());
            if (result == null) {
                return false;
            }
            String error = (String) result.get("error");
            if (error == null || error.isEmpty()) {
            } else {
                errorInfo = error;
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

    //修改手机 和邮箱
    public boolean changeEmailAndPhone(String email, String phone) {
        errorInfo = "";
        String URL = "user_edit.action";
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
                errorInfo = error;
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //地址操作
    //新增地址
    public boolean addAddress(BeanAddress beanAddress) {
        String URL = "address_add.action";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("useraccount", beanUser.getUsername());
            info.put("addrlocation", beanAddress.getLocation());
            info.put("addrinfo", beanAddress.getInfo());
            info.put("addruser", beanAddress.getName());
            info.put("addrphone", beanAddress.getPhone());
            if (beanAddress.isAddrDefault()) {
                info.put("addrdefault", 1);
            } else {
                info.put("addrdefault", 0);
            }

            Log.i(TAG, info.toString());

            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                int addrid = result.getInt("addrId");
                beanAddress.setAddrId(addrid);
                addressList.add(beanAddress);
                if (beanAddress.isAddrDefault()) {
                    BeanAddress.indexDeault = addressList.size() - 1;
                }
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //删除地址
    public boolean deleteAddress(int addrId) {
        String URL = "address_delete.action";
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
            Log.i(TAG, "deleteAddress:error" + error);
            if (error == null || error.isEmpty()) {
                for (int i = 0; i < addressList.size(); i++) {
                    if (addrId == addressList.get(i).getAddrId()) {
                        addressList.remove(i);
                        if (i < BeanAddress.indexDeault) {
                            BeanAddress.indexDeault -= 1;
                        }
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
        String URL = "address_edit.action";
        //把bean对象封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("addrid", beanAddress.getAddrId());
            info.put("useraccount", beanUser.getUsername());
            info.put("addrlocation", beanAddress.getLocation());
            info.put("addrinfo", beanAddress.getInfo());
            info.put("addruser", beanAddress.getName());
            info.put("addrphone", beanAddress.getPhone());
            Log.i(TAG, "changeAddress_info:" + info);

            JSONObject result = httpControl.postMethod(info, URL);
            if (result == null) {
                return false;
            }
            String error = result.getString("error");
            Log.i(TAG, "changeAddress_error:" + error);
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
        String URL = "address_editDefault.action";
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
            Log.i(TAG, "changeDefaultAddress_error:" + error);
            if (error == null || error.isEmpty()) {
                for (int i = 0; i < addressList.size(); i++) {
                    if (addrId == addressList.get(i).getAddrId()) {
                        addressList.get(BeanAddress.indexDeault).setAddrDefault(false);
                        //找到对应地址
                        BeanAddress.indexDeault = i;
                        addressList.get(i).setAddrDefault(true);
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

    //订单操作
    //获取位置周边订单信息
    public boolean getNearOrder(LatLng position) {
        //获取当前位置下的订单
        errorInfo = "";
        String URL = "order_searchNear.action";
        nearTaskList.clear();

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
            Log.i(TAG, "getNearTask_result:" + result);
            if (result == null) {
                return false;
            }
            String error = (String) result.get("error");
            Log.i(TAG, "getNearTask_error:" + error);
            if (error == null || error.isEmpty()) {
                JSONArray list = result.getJSONArray("orderList");
                for (int i = 0; i < list.length(); i++) {
                    JSONArray listItem = list.getJSONArray(i);
                    BeanOrder beanOrder = new BeanOrder();
                    beanOrder.setOrderId(listItem.getInt(0));
                    beanOrder.setOrderName(listItem.getString(1));
                    beanOrder.setOrderText(listItem.getString(2));
                    beanOrder.setBounty(listItem.getDouble(3));
                    beanOrder.setDistence(listItem.getDouble(4));
                    nearTaskList.add(beanOrder);
                }
                Log.i(TAG, "getNearTask_nearTaskList:" + nearTaskList.toString());
            } else {
                errorInfo = error;
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    //获取单个订单的详情
    public boolean getOrderItem(int orderId) {
        String URL = "order_get.action";
        int position = 0;
        for (int i = 0; i < nearTaskList.size(); i++) {
            if (nearTaskList.get(i).getOrderId() == orderId) {
                position = i;
            }
        }
        BeanOrder beanOrder = nearTaskList.get(position);
        Log.i(TAG, "getOrderItem_position:" + position);
        //把数据封装成JSON
        JSONObject info = new JSONObject();
        try {
            info.put("check", "remeber_client");
            info.put("orderid", orderId);
            JSONObject result = httpControl.postMethod(info, URL);
            Log.i(TAG, "getOrderItem_result:" + result);
            if (result == null) {
                return false;
            }
            String error = (String) result.get("error");
            Log.i(TAG, "getOrderItem_error:" + error);
            if (error == null || error.isEmpty()) {
                JSONObject order = result.getJSONObject("order");
                beanOrder.setBounty(order.getDouble("orderBounty"));
                BeanUser empUser = new BeanUser();

                String orderStartTime = order.getString("orderStartTime");
                if (orderStartTime != null && !orderStartTime.isEmpty() && !orderStartTime.equals("null")) {
                    Calendar startTime = Calendar.getInstance();
                    startTime.setTime(format.parse(order.getString("orderStartTime")));
                    beanOrder.setStartTime(startTime);
                }
                String orderEndTime = order.getString("orderEndTime");
                if (orderEndTime != null && !orderEndTime.isEmpty() && !orderEndTime.equals("null")) {
                    Calendar endTime = Calendar.getInstance();
                    endTime.setTime(format.parse(order.getString("orderEndTime")));
                    beanOrder.setEndTime(endTime);
                }

                beanOrder.setEvalBoss(order.getString("orderEvalBoss"));
                beanOrder.setEvalEmp(order.getString("orderEvalEmp"));
                beanOrder.setMoney(order.getDouble("orderMoney"));
                beanOrder.setOrderName(order.getString("orderName"));
                beanOrder.setOrderText(order.getString("orderText"));

                JSONObject address = order.getJSONObject("address");
                BeanAddress beanAddress = new BeanAddress();
                beanAddress.setAddrId(address.getInt("addrId"));
                beanAddress.setInfo(address.getString("addrInfo"));
                beanAddress.setLocation(address.getString("addrLocation"));
                beanAddress.setPhone(address.getString("addrPhone"));
                beanAddress.setName(address.getString("addrUser"));
                beanOrder.setAddress(beanAddress);

                JSONArray thing = order.getJSONArray("thing");
                List<BeanThing> thingList = new ArrayList<BeanThing>();
                for (int i = 0; i < thing.length(); i++) {
                    JSONObject thingItem = thing.getJSONObject(i);
                    BeanThing beanThing = new BeanThing();
                    beanThing.setThingid(thingItem.getInt("thingId"));
//                    if (thingItem.getInt("thingBuy") == 1) {
//                        beanThing.setBuy(true);
//                    } else {
//                        beanThing.setBuy(false);
//                    }
                    beanThing.setLatitude(thingItem.getDouble("thingLatitude"));
                    beanThing.setLongitude(thingItem.getDouble("thingLongitude"));
                    beanThing.setAddress(thingItem.getString("thingLocation"));
                    beanThing.setMoney(thingItem.getDouble("thingMoney"));
                    beanThing.setName(thingItem.getString("thingName"));
                    beanThing.setNumber(thingItem.getInt("thingNum"));
                    thingList.add(beanThing);
                }
                beanOrder.setThingList(thingList);

                JSONObject user = order.getJSONObject("user");
                BeanUser beanUser = new BeanUser();
                beanUser.setUsername(user.getString("userAccount"));
                beanUser.setEmail(user.getString("userEmail"));
                beanUser.setGood(user.getInt("userGood"));
                beanUser.setName(user.getString("userName"));
                beanUser.setPhone(user.getString("userPhone"));
                beanUser.setSex(user.getString("userSex"));
                beanOrder.setBossAccount(beanUser);

                nearTaskList.set(position, beanOrder);
                Log.i(TAG, "nearTaskList_position:" + nearTaskList.get(position));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void sortOrderList() {
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
                if (beanOrder.getEmpAccount().getUsername() == beanUser.getUsername()) {
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
        String URL = "order_add.action";
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
            Log.i(TAG, "addOrder_info:" + info.toString());
            JSONObject result = httpControl.postMethod(info, URL);
            Log.i(TAG, "addOrder_result:" + result.toString());
            if (result == null) {
                return -1;
            }
            String error = result.getString("error");
            if (error == null || error.isEmpty()) {
                orderid = result.getInt("orderId");
                order.setOrderId(orderid);
                myOrderList.add(order);
                sortOrderList();
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
                orderid = result.getInt("orderId");
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
        errorInfo = "";
        //获取当前位置下的订单
        String URL = "order_receive.action";
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
                beanAddress.setLocation(result.getString("addrLocation"));
                beanAddress.setInfo(result.getString("addrInfo"));
                beanAddress.setName(result.getString("addrUser"));
                beanAddress.setPhone(result.getString("addrPhone"));
                order.setAddress(beanAddress);
                order.setEmpAccount(beanUser);
            } else {
                errorInfo = error;
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beanAddress;
    }

    //完成订单
    public Calendar completeOrder(BeanOrder order) {
        Calendar instance = Calendar.getInstance();
        String URL = "order_complete.action";
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
                String orderendtime = result.getString("orderEndTime");
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