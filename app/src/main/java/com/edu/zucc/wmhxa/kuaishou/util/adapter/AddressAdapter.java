package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

import java.util.List;
import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */


public class AddressAdapter extends BaseAdapter {
    Context context;
    Map<String, String> map;

    public AddressAdapter(Context context, Map<String, String> map) {
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

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_address, parent);
        } else {
            view = convertView;
        }

        TextView addName = (TextView) view.findViewById(R.id.item_tv_addname);
        TextView addPhone = (TextView) view.findViewById(R.id.item_tv_addphone);
        TextView addAddress = (TextView) view.findViewById(R.id.item_tv_addaddress);

        addName.setText(map.get("username"));
        addPhone.setText(map.get("phone"));
        addAddress.setText(map.get("address"));

        return view;
    }
}
