package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class ThingListAdapter extends BaseAdapter {
    Context context;
    Map<String, String> map;

    public ThingListAdapter(Context context, Map<String, String> map) {
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
        TextView thingName =(TextView) view.findViewById(R.id.thinglist_tv_task);
        TextView thingPlace =(TextView) view.findViewById(R.id.thinglist_tv_place);
        TextView thingMoney =(TextView) view.findViewById(R.id.thinglist_tv_money);

        thingName.setText(map.get("thingname"));
        thingPlace.setText(map.get("thingplace"));
        thingMoney.setText(map.get("thingmoney"));


        return view;
    }
}
