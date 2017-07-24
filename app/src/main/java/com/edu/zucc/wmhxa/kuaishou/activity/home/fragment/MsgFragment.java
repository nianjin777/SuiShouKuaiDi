package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.util.ListViewUtil;
import com.edu.zucc.wmhxa.kuaishou.util.adapter.MessageAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/7/19.
 */

public class MsgFragment extends Fragment {

    private View view;
    private ListView msg_lv_orders;
    private ListView msg_lv_friends;
    private List<Map<String, Object>> orderList;
    private List<Map<String, Object>> friendsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_msg, container, false);

        initData();
        findViewById();

        return view;
    }

    private void findViewById() {
        msg_lv_orders = (ListView) view.findViewById(R.id.msg_lv_orders);
        msg_lv_orders.setAdapter(new MessageAdapter(getContext(), orderList));
        ListViewUtil.setListViewHeightBasedOnChildren(msg_lv_orders);
        msg_lv_friends = (ListView) view.findViewById(R.id.msg_lv_friends);
        msg_lv_friends.setAdapter(new MessageAdapter(getContext(), friendsList));
        ListViewUtil.setListViewHeightBasedOnChildren(msg_lv_friends);
    }

    private void initData() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        //orders 数据
        orderList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("friendname", String.valueOf(i));
            List<String> msglist = new ArrayList<String>();
            msglist.add("哈哈哈1");
            msglist.add("哈哈哈2");
            msglist.add("哈哈哈3");
            map.put("message", msglist);
            map.put("time", format.format(new Date()));
            orderList.add(map);
        }
        //friends 数据
        friendsList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("friendname", String.valueOf(i));
            List<String> msglist = new ArrayList<String>();
            msglist.add("哈哈哈1");
            msglist.add("哈哈哈2");
            msglist.add("哈哈哈3");
            map.put("message", msglist);
            map.put("time", format.format(new Date()));
            friendsList.add(map);
        }
    }

}
