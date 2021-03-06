package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.model.BeanThing;

import java.util.List;

/**
 * Created by wssb on 2017/7/21.
 */

public class ThingListAdapter extends BaseAdapter {
    Context context;
    List<BeanThing> list;


    public ThingListAdapter(Context context, List<BeanThing> list) {
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
            view = View.inflate(context, R.layout.item_thinglist, null);
        } else {
            view = convertView;
        }
        TextView thingName = (TextView) view.findViewById(R.id.thinglist_tv_name);
        TextView thingPlace = (TextView) view.findViewById(R.id.thinglist_tv_place);
        TextView thingMoney = (TextView) view.findViewById(R.id.thinglist_tv_money);
        TextView thingNumber = (TextView) view.findViewById(R.id.thinglist_et_number);

        BeanThing beanThing = list.get(position);

        thingName.setText(beanThing.getName());
        thingPlace.setText(beanThing.getAddress());
        thingMoney.setText(String.valueOf(beanThing.getMoney()));
        thingNumber.setText(String.valueOf(beanThing.getNumber()));

        return view;
    }
}
