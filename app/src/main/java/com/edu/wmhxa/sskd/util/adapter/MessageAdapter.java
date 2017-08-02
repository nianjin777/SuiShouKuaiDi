package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.edu.wmhxa.sskd.R;

import java.util.List;
import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class MessageAdapter extends BaseAdapter {
    Context context;
    List<Map<String, Object>> list;

    public MessageAdapter(Context context, List<Map<String, Object>> list) {
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
            view = View.inflate(context, R.layout.item_message, null);
        } else {
            view = convertView;
        }

        Map<String, Object> map = list.get(position);

        ImageView headPic = (ImageView) view.findViewById(R.id.message_iv_head);
        TextView name = (TextView) view.findViewById(R.id.message_tv_name);
        TextView message = (TextView) view.findViewById(R.id.message_tv_mes);
        TextView time = (TextView) view.findViewById(R.id.message_tv_time);

        List<String> msgList = (List<String>) map.get("message");

        name.setText((String) map.get("friendname"));
        message.setText(msgList.get(0));
        time.setText((String) map.get("time"));

        return view;
    }
}
