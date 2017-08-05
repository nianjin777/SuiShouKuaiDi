package com.edu.wmhxa.sskd.util.adapter;

import android.content.Context;
import android.content.Intent;
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

import static android.R.attr.start;

/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderFinishAdapter extends BaseAdapter {

    private Context context = null;
    private List<BeanOrder> list = null;
    private View view;
    private TextView orderfinish_empname;
    private TextView orderfinish_tv_task;
    private TextView orderfinish_tv_text;
    private TextView orderfinish_tv_money;
    private Button orderfinish_bt_friend;
    private Button orderfinish_bt_evaluate;

    public OrderFinishAdapter(Context context, List<BeanOrder> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        view = null;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_order_finish, null);
        } else {
            view = convertView;
        }

        final BeanOrder beanOrder = list.get(position);
        findViewById();
        setListener();

        orderfinish_empname.setText(beanOrder.getEmpAccount().getName());
        orderfinish_tv_task.setText(beanOrder.getOrderName());
        orderfinish_tv_text.setText(beanOrder.getOrderText());
        orderfinish_tv_money.setText(String.valueOf(beanOrder.getMoney()));

        orderfinish_bt_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 加为好友
            }
        });
        orderfinish_bt_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 评价
                Intent intent = new Intent();
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.order.evaluate.EvaluateActivity");
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        return view;
    }

    private void findViewById() {
        orderfinish_empname = (TextView) view.findViewById(R.id.orderfinish_empname);
        orderfinish_tv_task = (TextView) view.findViewById(R.id.orderfinish_tv_taskname);
        orderfinish_tv_text = (TextView) view.findViewById(R.id.orderfinish_tv_text);
        orderfinish_tv_money = (TextView) view.findViewById(R.id.orderfinish_tv_money);
        orderfinish_bt_friend = (Button) view.findViewById(R.id.orderfinish_bt_evaluate);
        orderfinish_bt_evaluate = (Button) view.findViewById(R.id.orderfinish_bt_evaluate);
    }

    private void setListener() {
        orderfinish_bt_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
