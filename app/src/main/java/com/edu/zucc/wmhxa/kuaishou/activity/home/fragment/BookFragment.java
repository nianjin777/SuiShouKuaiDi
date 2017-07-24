package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.util.ListViewUtil;
import com.edu.zucc.wmhxa.kuaishou.util.adapter.FriendAdapter;
import com.edu.zucc.wmhxa.kuaishou.util.adapter.HandleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/19.
 */

public class BookFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ListView book_lv_friends;
    private ListView book_lv_handle;
    private List<String> list;
    private List<Map<String, String>> friendsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_book, container, false);

        initData();
        findViewById();
        setListener();

        return view;
    }

    private void initData() {
        list = new ArrayList<String>();
        list.add("添加好友");

        friendsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 20; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("friendname", "好友 " + i);
            map.put("friendlevel", "" + i);
            friendsList.add(map);
        }

    }

    private void findViewById() {
        book_lv_handle = (ListView) view.findViewById(R.id.book_lv_handle);
        book_lv_handle.setAdapter(new HandleAdapter(getContext(), list));
        ListViewUtil.setListViewHeightBasedOnChildren(book_lv_handle);
        book_lv_friends = (ListView) view.findViewById(R.id.book_lv_friends);
        book_lv_friends.setAdapter(new FriendAdapter(getContext(), friendsList));
        ListViewUtil.setListViewHeightBasedOnChildren(book_lv_friends);
    }

    private void setListener() {
        book_lv_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
//                intent.setClassName("com.edu.zucc.wmhxa.kuaishou",)
                //TODO 跳转到聊天界面
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
