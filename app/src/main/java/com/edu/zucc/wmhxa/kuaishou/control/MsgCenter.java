package com.edu.zucc.wmhxa.kuaishou.control;

import com.edu.zucc.wmhxa.kuaishou.model.BeanThing;
import com.edu.zucc.wmhxa.kuaishou.model.BeanUser;

import java.util.List;
import java.util.Map;

/**
 * Created by KarlCyan on 2017/7/25.
 * <p>
 * 消息中心，服务器获取到的数据都保存在这个类中
 */

public class MsgCenter {

    //当前登陆用户
    private static BeanUser beanUser = null;

    /**
     *      附近任务表
     *          用户获取到位置后会向服务器拉取附近所有任务的任务信息存在List中
     */
    private static List<Map<String, Object>> nearTaskList = null;

    //订单详情
    private static Map<String, Object> orderInfo = null;

    //物品清单
    private static List<BeanThing> thingList = null;

}
