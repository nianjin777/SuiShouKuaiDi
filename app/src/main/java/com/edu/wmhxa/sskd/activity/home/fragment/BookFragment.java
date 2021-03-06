package com.edu.wmhxa.sskd.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.comm.ApplyFriendActivity;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanUser;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.FriendAdapter;
import com.edu.wmhxa.sskd.util.adapter.HandleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class BookFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ListView book_lv_friends;
    private ListView book_lv_handle;
    private List<String> list;
    private List<BeanUser> friendsList;
    private FriendAdapter friendAdapter;

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
        list.add("新的朋友");
        friendsList = MsgCenter.friendList;

    }

    private void findViewById() {
        book_lv_handle = (ListView) view.findViewById(R.id.book_lv_handle);
        book_lv_handle.setAdapter(new HandleAdapter(getContext(), list));
        ListViewUtil.setListViewHeightBasedOnChildren(book_lv_handle);
        book_lv_friends = (ListView) view.findViewById(R.id.book_lv_friends);
        friendAdapter = new FriendAdapter(getContext(), friendsList);
        book_lv_friends.setAdapter(friendAdapter);
        ListViewUtil.setListViewHeightBasedOnChildren(book_lv_friends);
    }

    private void setListener() {
        book_lv_friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到聊天界面
                Intent intent = new Intent();
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.comm.DetailFriendActivity");
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        book_lv_handle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        Intent jump = new Intent(getActivity(), ApplyFriendActivity.class);
                        startActivity(jump);
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
