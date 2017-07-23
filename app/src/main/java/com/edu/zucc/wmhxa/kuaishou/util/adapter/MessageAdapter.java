package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class MessageAdapter extends BaseAdapter {
    Context context;
    Map<String, String> map;

    public MessageAdapter(Context context, Map<String, String> map) {
        this.context = context;
        this.map = map;
    }

    @Override
    public int getCount() {
        return map.size();
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
            view = View.inflate(context, R.layout.item_address, parent);
        } else {
            view = convertView;
        }
        ImageView headPic =(ImageView) view.findViewById(R.id.message_iv_head);
        TextView name =(TextView) view.findViewById(R.id.message_tv_name);
        TextView message = (TextView) view.findViewById(R.id.message_tv_mes);
        TextView time =(TextView) view.findViewById(R.id.message_tv_time) ;

        name.setText(map.get("friendname"));
        message.setText(map.get("message"));
        time.setText(map.get("time"));

        return view;
    }
}
