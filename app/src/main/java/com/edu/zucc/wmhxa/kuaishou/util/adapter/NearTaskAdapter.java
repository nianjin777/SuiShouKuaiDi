package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

public class NearTaskAdapter extends BaseAdapter {
    Context context;
    List<Map<String, String>> list = null;

    public NearTaskAdapter(Context context, List<Map<String, String>> list) {
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
            view = View.inflate(context, R.layout.item_neartask, null);
        } else {
            view = convertView;
        }
        TextView taskNmae = (TextView) view.findViewById(R.id.neartask_tv_task);
        TextView taskDis = (TextView) view.findViewById(R.id.neartask_tv_dis);
        TextView taskText = (TextView) view.findViewById(R.id.neartask_tv_text);
        TextView taskMoney = (TextView) view.findViewById(R.id.neartask_tv_money);

        Map<String, String> map = list.get(position);
        taskNmae.setText(map.get("taskname"));
        taskDis.setText(map.get("distance"));//距离
        taskText.setText(map.get("text"));
        taskMoney.setText(map.get("money"));


        return view;
    }
}
