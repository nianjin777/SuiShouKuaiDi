package com.edu.wmhxa.sskd.activity.order.accept;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcceptOrderActivity extends Activity implements View.OnClickListener {
    private TextView accept_tv_taskname;
    private ListView accept_lv_thing;
    private TextView accrpt_tv_text;
    private TextView accept_tv_money;
    private TextView accept_tv_totle;
    private TextView accept_tv_addr;
    private Button accept_bt_back;
    private Button accept_bt_accept;
    private BeanOrder order;
    private ImageView back;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BeanAddress beanAddress = (BeanAddress) msg.obj;
                    if (beanAddress == null) {
                        Toast.makeText(getApplicationContext(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        order.setAddress(beanAddress);
                        MsgCenter.empOrderList.add(order);
                        Toast.makeText(getApplicationContext(), "接受订单成功\n请在我的订单-正在进行的订单中查看详情", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                case 100:
                    boolean result = (boolean) msg.obj;
                    if (result) {
                        order = MsgCenter.nearTaskList.get(position);
                        findViewById();
                        setListener();
                    } else {
                        Toast.makeText(getApplicationContext(), "订单信息拉取失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        }
    };
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept);
        //拿到选择任务的信息Map
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        order = MsgCenter.nearTaskList.get(position);
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = MsgCenter.getInstanceMsgCenter().getOrderItem(order.getOrderId());
                Message message = new Message();
                message.what = 100;
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void findViewById() {
        //设置标题
        View title = findViewById(R.id.accept_title);
        back = (ImageView) title.findViewById(R.id.title1_back);
        TextView title1_tv = (TextView) title.findViewById(R.id.title1_tv);
        title1_tv.setText("订单详情");

        List<BeanThing> things = order.getThingList();
        double totle = order.getBounty();
        for (BeanThing beanThing : things) {
            totle += beanThing.getMoney();
        }

        //大致地址
        accept_tv_addr = (TextView) findViewById(R.id.accept_tv_addr);
        accept_tv_addr.setText(order.getAddress().getLocation());
        //任务名
        accept_tv_taskname = (TextView) findViewById(R.id.accept_tv_taskname);
        accept_tv_taskname.setText(order.getOrderName());
        //物品清单
        accept_lv_thing = (ListView) findViewById(R.id.accept_lv_thing);
        accept_lv_thing.setAdapter(new ThingListAdapter(getApplicationContext(), things));
        ListViewUtil.setListViewHeightBasedOnChildren(accept_lv_thing);
        //备注
        accrpt_tv_text = (TextView) findViewById(R.id.accrpt_tv_text);
        accrpt_tv_text.setText(order.getOrderText());
        //金额
        accept_tv_money = (TextView) findViewById(R.id.accept_tv_money);
        accept_tv_money.setText(String.valueOf(order.getBounty()));
        //总价
        accept_tv_totle = (TextView) findViewById(R.id.accept_tv_totle);
        accept_tv_totle.setText(String.valueOf(totle));

        accept_bt_back = (Button) findViewById(R.id.accept_bt_back);
        accept_bt_accept = (Button) findViewById(R.id.accept_bt_accept);
    }

    private void setListener() {
        accept_bt_back.setOnClickListener(this);
        accept_bt_accept.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accept_bt_back:
                finish();
                break;

            case R.id.accept_bt_accept:
                //接受任务
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BeanAddress beanAddress = MsgCenter.getInstanceMsgCenter().acceptOrder(order);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = beanAddress;
                        handler.sendMessage(message);
                    }
                }).start();
                break;

            case R.id.title1_back:
                finish();
                break;
        }
    }
}
