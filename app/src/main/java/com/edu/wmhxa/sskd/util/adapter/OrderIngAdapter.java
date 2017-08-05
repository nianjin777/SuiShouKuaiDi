package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanOrder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderIngAdapter extends BaseAdapter {

    private Context context = null;
    private List<BeanOrder> list = null;
    private View view;

    private TextView ordering_empname;
    private TextView ordering_tv_task;
    private TextView ordering_tv_text;
    private TextView ordering_tv_dis;
    private TextView ordering_tv_money;
    private Button ordering_bt_cancel;
    private Button ordering_bt_ok;
    private TextView ordering_state;

    public OrderIngAdapter(Context context, List<BeanOrder> list) {
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
            view = View.inflate(context, R.layout.item_order_ing, null);
        } else {
            view = convertView;
        }

        findViewById();
        if (position < MsgCenter.empOrderList.size()) {
            ordering_state.setText("正在负责快递");
        } else if (position >= MsgCenter.empOrderList.size()) {
            ordering_state.setText("正在进行");
        }

        BeanOrder beanOrder = list.get(position);
        ordering_empname.setText(beanOrder.getEmpAccount().getName());
        ordering_tv_task.setText(beanOrder.getOrderName());
        ordering_tv_text.setText(beanOrder.getOrderText());
        ordering_tv_dis.setText(String.valueOf(beanOrder.getDistence()));
        ordering_tv_money.setText(String.valueOf(beanOrder.getMoney()));

        ordering_bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 取消订单
            }
        });
        ordering_bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 确认收货
            }
        });

        return view;
    }

    private void findViewById() {
        ordering_empname = (TextView) view.findViewById(R.id.ordering_empname);
        ordering_tv_task = (TextView) view.findViewById(R.id.ordering_tv_taskname);
        ordering_tv_text = (TextView) view.findViewById(R.id.ordering_tv_text);
        ordering_tv_dis = (TextView) view.findViewById(R.id.ordering_tv_dis);
        ordering_tv_money = (TextView) view.findViewById(R.id.ordering_tv_money);
        ordering_bt_cancel = (Button) view.findViewById(R.id.ordering_bt_cancel);
        ordering_bt_ok = (Button) view.findViewById(R.id.ordering_bt_ok);
        ordering_state = (TextView) view.findViewById(R.id.ordering_state);
    }

}
