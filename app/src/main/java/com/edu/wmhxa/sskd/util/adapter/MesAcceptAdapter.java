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
 * Created by wssb on 2017/8/6.
 */

public class MesAcceptAdapter extends BaseAdapter {
    Context context;
    List<Map<String, Object>> list;
    public MesAcceptAdapter(Context context, List<Map<String, Object>> list) {
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
            view = View.inflate(context, R.layout.item_mesaccept, null);
        } else {
            view = convertView;
        }

        ImageView headPic = (ImageView) view.findViewById(R.id.chatlist_iv_other);
        TextView otherMes = (TextView) view.findViewById(R.id.chatlist_tv_other);


        return view;
    }
}
