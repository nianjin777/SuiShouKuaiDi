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
                    beanOrder.setEmpAccount(order.getString("orderEmp"));

                    Calendar startTime = Calendar.getInstance();
                    startTime.setTime(format.parse(order.getString("orderStartTime")));
                    beanOrder.setStartTime(startTime);
                    Calendar endTime = Calendar.getInstance();
                    endTime.setTime(format.parse(order.getString("orderEndTime")));
                    beanOrder.setEndTime(endTime);

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
                    int addrDefault = addressObject.getInt("addrDefault");
                    if (addrDefault == 1) {
                        beanAddress.setAddrDefault(true);
                    } else {
                        beanAddress.setAddrDefault(false);
                    }
                    beanAddress.setAddrId(addressObject.getInt("addrId"));
                    beanAddress.setInfo(addressObject.getString("addrInfo"));
                    beanAddress.setLocation(addressObject.getString("addrLocation"));
                    beanAddress.setPhone(addressObject.getString("addrPhone"));
                    beanAddress.setName(addressObject.getString("addrUser"));
                    beanOrder.setAddress(beanAddress);

                    myOrderList.add(beanOrder);
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sortOrderList();
                    }
                }).start();
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
        String URL = "address_edit.action";
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