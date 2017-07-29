package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.model.BeanUser;

import java.util.List;
import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class FriendAdapter extends BaseAdapter {
    Context context;
    List<BeanUser> list;

    public FriendAdapter(Context context, List<BeanUser> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_friend, null);
        } else {
            view = convertView;
        }

        ImageView headPic = (ImageView) view.findViewById(R.id.friend_iv_head);
        TextView name = (TextView) view.findViewById(R.id.friend_tv_name);
        TextView level = (TextView) view.findViewById(R.id.friend_tv_level);

        BeanUser beanUser = list.get(position);

        name.setText(beanUser.getName());
        level.setText(String.valueOf(beanUser.getGood()));
        return view;
    }
}
