package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class WaitEvalAdapter extends BaseAdapter {
    Context context;
    Map<String, String> map;

    public WaitEvalAdapter(Context context, Map<String, String> map) {
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

        TextView waiter = (TextView) view.findViewById(R.id.itemwait_name);
        TextView taskNmae = (TextView) view.findViewById(R.id.itemwait_tv_task);
        TextView taskdis = (TextView) view.findViewById(R.id.itemwait_tv_dis);
        TextView taskMonsy = (TextView) view.findViewById(R.id.itemwait_tv_money);
        TextView taskText = (TextView) view.findViewById(R.id.itemwait_tv_text);
        Button getFriend = (Button) view.findViewById(R.id.itemwait_bt_friend);
        Button evaluate = (Button) view.findViewById(R.id.itemwait_bt_evaluate);

        waiter.setText(map.get("waitername"));
        taskNmae.setText(map.get("taskname"));
        taskdis.setText(map.get("farestway"));
        taskMonsy.setText(map.get("money"));
        taskText.setText(map.get("text"));


        return view;
    }
}
