package com.edu.wmhxa.sskd.activity.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.OrderFinishAdapter;
import com.edu.wmhxa.sskd.util.adapter.OrderIngAdapter;
import com.edu.wmhxa.sskd.util.adapter.OrderWaitAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends Activity {

    private View ordewr_title1;
    private TextView title1_tv;
    private static ListView order_lv_wait;
    private static ListView order_lv_ing;
    private static ListView order_lv_finish;
    private ImageView back;

    private static List<BeanOrder> ingList = new ArrayList<BeanOrder>();

    public static Handler freshHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);

        findViewById();
        setListener();
    }

    public void findViewById() {
        ordewr_title1 = findViewById(R.id.ordewr_title1);
        title1_tv = (TextView) ordewr_title1.findViewById(R.id.title1_tv);
        title1_tv.setText("我的订单");
        back = (ImageView) ordewr_title1.findViewById(R.id.title1_back);

        ingList.addAll(MsgCenter.empOrderList);
        ingList.addAll(MsgCenter.ingOrderList);

        //未完成订单
        order_lv_wait = (ListView) findViewById(R.id.order_lv_wait);
        order_lv_wait.setAdapter(new OrderWaitAdapter(getApplicationContext(), MsgCenter.waitOrderList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_wait);

        //待评价订单
        order_lv_ing = (ListView) findViewById(R.id.order_lv_ing);
        order_lv_ing.setAdapter(new OrderIngAdapter(getApplicationContext(), ingList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_ing);

        //已完成订单
        order_lv_finish = (ListView) findViewById(R.id.order_lv_finish);
        order_lv_finish.setAdapter(new OrderFinishAdapter(getApplicationContext(), MsgCenter.finishOrderList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_finish);

    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        order_lv_wait.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyOrderActivity.this, SeeOrderActivity.class);
                intent.putExtra("type", "wait");
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });

        order_lv_ing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyOrderActivity.this, SeeOrderActivity.class);
                intent.putExtra("type", "ing");
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });

        order_lv_finish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyOrderActivity.this, SeeOrderActivity.class);
                intent.putExtra("type", "finish");
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
    }

    public static void freshLV(Context context) {
        ingList.clear();
        ingList.addAll(MsgCenter.empOrderList);
        ingList.addAll(MsgCenter.ingOrderList);
        //未完成订单
        order_lv_wait.setAdapter(new OrderWaitAdapter(context, MsgCenter.waitOrderList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_wait);

        //待评价订单
        order_lv_ing.setAdapter(new OrderIngAdapter(context, ingList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_ing);

        //已完成订单
        order_lv_finish.setAdapter(new OrderFinishAdapter(context, MsgCenter.finishOrderList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_finish);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //未完成订单
        order_lv_wait.setAdapter(new OrderWaitAdapter(getApplicationContext(), MsgCenter.waitOrderList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_wait);

        //待评价订单
        order_lv_ing.setAdapter(new OrderIngAdapter(getApplicationContext(), ingList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_ing);

        //已完成订单
        order_lv_finish.setAdapter(new OrderFinishAdapter(getApplicationContext(), MsgCenter.finishOrderList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_finish);
    }
}
