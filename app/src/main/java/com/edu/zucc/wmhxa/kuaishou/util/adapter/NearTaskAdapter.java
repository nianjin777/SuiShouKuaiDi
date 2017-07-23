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

public class NearTaskAdapter extends BaseAdapter {
    Context context;
    Map<String, String> map;

    public NearTaskAdapter(Context context, Map<String, String> map) {
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
        TextView taskNmae=(TextView) view.findViewById(R.id.neartask_tv_task);
        TextView taskdis =(TextView) view.findViewById(R.id.neartask_tv_dis);
        TextView taskText =(TextView) view.findViewById(R.id.neartask_tv_text);
        TextView taskMoney=(TextView) view.findViewById(R.id.neartask_tv_money);

        taskNmae.setText(map.get("taskname"));
        taskdis.setText(map.get("farestdis"));
        taskText.setText(map.get("text"));
        taskMoney.setText(map.get("money"));


        return view;
    }
}
