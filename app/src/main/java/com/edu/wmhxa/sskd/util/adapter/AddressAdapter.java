package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.model.BeanAddress;

import java.util.List;
import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */


public class AddressAdapter extends BaseAdapter {
    Context context;
    List<BeanAddress> list;

    public AddressAdapter(Context context, List<BeanAddress> list) {
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

        BeanAddress beanAddress = list.get(position);

        addName.setText(beanAddress.getName());
        addPhone.setText(beanAddress.getPhone());
        addAddress.setText(beanAddress.getLocation() + "\n" + beanAddress.getInfo());

        return view;
    }
}
