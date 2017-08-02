package com.edu.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.edu.wmhxa.kuaishou.R;

import java.util.Map;

/**
 * Created by wssb on 2017/7/21.
 */

public class EditAddAdapter extends BaseAdapter {
    Context context;
    Map<String, String> map;

    public EditAddAdapter(Context context, Map<String, String> map) {
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

        TextView editName = (TextView) view.findViewById(R.id.item_tv_editname);
        TextView editPhone = (TextView) view.findViewById(R.id.item_tv_editphone);
        TextView editAddress = (TextView) view.findViewById(R.id.item_tv_editaddress);
        Button btEdit = (Button) view.findViewById(R.id.edit_bt_edit);
        Button btDel = (Button) view.findViewById(R.id.edit_bt_delete);

        editName.setText(map.get("username"));
        editPhone.setText(map.get("userphone"));
        editAddress.setText(map.get("useraddress"));
        btDel.setText(map.get("删除"));
        btEdit.setText(map.get("编辑"));


        return view;
    }
}
