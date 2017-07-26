package com.edu.zucc.wmhxa.kuaishou.util.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderFinishAdapter extends BaseAdapter {

    private Context context = null;
    private List<Map<String, Object>> list = null;
    private View view;
    private TextView orderfinish_empname;
    private TextView orderfinish_tv_task;
    private TextView orderfinish_tv_text;
    private TextView orderfinish_tv_dis;
    private TextView orderfinish_tv_money;
    private Button orderfinish_bt_friend;
    private Button orderfinish_bt_evaluate;

    public OrderFinishAdapter(Context context, List<Map<String, Object>> list) {
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
            view = View.inflate(context, R.layout.item_order_finish, null);
        } else {
            view = convertView;
        }

        Map<String, Object> map = list.get(position);
        findViewById();

        orderfinish_empname.setText((String) map.get("empname"));
        orderfinish_tv_task.setText((String) map.get("taskname"));
        orderfinish_tv_text.setText((String) map.get("text"));
        orderfinish_tv_dis.setText(String.valueOf(map.get("distance")));
        orderfinish_tv_money.setText(String.valueOf(map.get("money")));

        orderfinish_bt_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 取消订单
            }
        });
        orderfinish_bt_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 确认收货
            }
        });

        return view;
    }

    private void findViewById() {
        orderfinish_empname = (TextView) view.findViewById(R.id.orderfinish_empname);
        orderfinish_tv_task = (TextView) view.findViewById(R.id.orderfinish_tv_taskname);
        orderfinish_tv_text = (TextView) view.findViewById(R.id.orderfinish_tv_text);
        orderfinish_tv_dis = (TextView) view.findViewById(R.id.orderfinish_tv_dis);
        orderfinish_tv_money = (TextView) view.findViewById(R.id.orderfinish_tv_money);
        orderfinish_bt_friend = (Button) view.findViewById(R.id.orderfinish_bt_evaluate);
        orderfinish_bt_evaluate = (Button) view.findViewById(R.id.orderfinish_bt_evaluate);
    }
}
