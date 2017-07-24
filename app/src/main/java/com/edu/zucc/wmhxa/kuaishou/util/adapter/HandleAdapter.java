package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.platform.comapi.map.L;
import com.edu.zucc.wmhxa.kuaishou.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class HandleAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public HandleAdapter(Context context, List<String> list) {
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
            view = View.inflate(context, R.layout.item_handle, null);
        } else {
            view = convertView;
        }

        TextView handle_text = (TextView) view.findViewById(R.id.handle_text);
        handle_text.setText(list.get(position));

        return view;
    }
}
