package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.model.BeanOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderWaitAdapter extends BaseAdapter {

    private Context context = null;
    private List<BeanOrder> list = null;
    private View view;
    private TextView orderwait_empname;
    private TextView orderwait_tv_task;
    private TextView orderwait_tv_text;
    private TextView orderwait_tv_money;
    private Button orderwait_bt_cancel;


    public OrderWaitAdapter(Context context, List<BeanOrder> list) {
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
        view = null;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_order_wait, null);
        } else {
            view = convertView;
        }

        findViewById();

        BeanOrder beanOrder = list.get(position);
//        orderwait_empname.setText(beanOrder.getEmpAccount().getName());
        orderwait_tv_task.setText(beanOrder.getOrderName());
        orderwait_tv_text.setText(beanOrder.getOrderText());
        orderwait_tv_money.setText(String.valueOf(beanOrder.getMoney()));

        orderwait_bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 取消订单
            }
        });

        return view;
    }

    private void findViewById() {
        orderwait_empname = (TextView) view.findViewById(R.id.orderwait_empname);
        orderwait_tv_task = (TextView) view.findViewById(R.id.orderwait_tv_taskname);
        orderwait_tv_text = (TextView) view.findViewById(R.id.orderwait_tv_text);
        orderwait_tv_money = (TextView) view.findViewById(R.id.orderwait_tv_money);
        orderwait_bt_cancel = (Button) view.findViewById(R.id.orderwait_bt_cancel);
    }
}
