package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.model.BeanUser;

import java.util.List;

/**
 * Created by wssb on 2017/7/25.
 */

public class FriApplyAdapter extends BaseAdapter {
    Context context;
    List<BeanUser> list;

    public FriApplyAdapter(Context context, List<BeanUser> list) {
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
            view = View.inflate(context, R.layout.item_friapply, null);
        } else {
            view = convertView;
        }
        BeanUser beanUser = list.get(position);

        ImageView friapply_iv_head = (ImageView) view.findViewById(R.id.friapply_iv_head);
        TextView friapply_tv_name = (TextView) view.findViewById(R.id.friapply_tv_name);
        TextView friapply_tv_level = (TextView) view.findViewById(R.id.friapply_tv_level);
        Button friapply_bt_apply = (Button) view.findViewById(R.id.friapply_bt_apply);
        friapply_bt_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 向服务器发送同意信息
            }
        });

        friapply_iv_head.setImageResource(R.drawable.shape);
        friapply_tv_name.setText(beanUser.getName());
        friapply_tv_level.setText(String.valueOf(beanUser.getGood()));

        return view;
    }
}
