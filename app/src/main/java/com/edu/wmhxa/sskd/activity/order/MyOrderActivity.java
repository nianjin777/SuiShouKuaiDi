package com.edu.wmhxa.sskd.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private ListView order_lv_wait;
    private ListView order_lv_ing;
    private ListView order_lv_finish;
    private ImageView back;

    private List<BeanOrder> waitList = new ArrayList<BeanOrder>();

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

        waitList.addAll(MsgCenter.empOrderList);
        waitList.addAll(MsgCenter.waitOrderList);
        //未完成订单
        order_lv_wait = (ListView) findViewById(R.id.order_lv_wait);
        order_lv_wait.setAdapter(new OrderWaitAdapter(getApplicationContext(), waitList));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_wait);
        //待评价订单
        order_lv_ing = (ListView) findViewById(R.id.order_lv_ing);
        order_lv_ing.setAdapter(new OrderIngAdapter(getApplicationContext(), MsgCenter.ingOrderList));
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
                Intent intent = new Intent(MyOrderActivity.this, OrderResultActivity.class);
                intent.putExtra("lv", "wait");
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        order_lv_ing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyOrderActivity.this, OrderResultActivity.class);
                intent.putExtra("lv", "ing");
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        order_lv_finish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyOrderActivity.this, OrderResultActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}
