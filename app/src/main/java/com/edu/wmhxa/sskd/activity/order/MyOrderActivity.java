package com.edu.wmhxa.sskd.activity.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.OrderFinishAdapter;
import com.edu.wmhxa.sskd.util.adapter.OrderIngAdapter;
import com.edu.wmhxa.sskd.util.adapter.OrderWaitAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderActivity extends Activity implements View.OnClickListener {

    private View ordewr_title1;
    private TextView title1_tv;
    private ListView order_lv_wait;
    private ListView order_lv_ing;
    private ListView order_lv_finish;
    private ImageView back;

    private List<BeanOrder> list = new ArrayList<BeanOrder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);

        initData();
        findViewById();
        setListener();
    }

    public void findViewById() {
        ordewr_title1 = findViewById(R.id.ordewr_title1);
        title1_tv = (TextView) ordewr_title1.findViewById(R.id.title1_tv);
        title1_tv.setText("我的订单");
        back = (ImageView) ordewr_title1.findViewById(R.id.title1_back);
        //未完成订单
        order_lv_wait = (ListView) findViewById(R.id.order_lv_wait);
        order_lv_wait.setAdapter(new OrderWaitAdapter(getApplicationContext(), list));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_wait);
        //待评价订单
        order_lv_ing = (ListView) findViewById(R.id.order_lv_ing);
        order_lv_ing.setAdapter(new OrderIngAdapter(getApplicationContext(), list));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_ing);
        //已完成订单
        order_lv_finish = (ListView) findViewById(R.id.order_lv_finish);
        order_lv_finish.setAdapter(new OrderFinishAdapter(getApplicationContext(), list));
        ListViewUtil.setListViewHeightBasedOnChildren(order_lv_finish);
    }

    private void setListener() {
        back.setOnClickListener(this);
        order_lv_wait.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        order_lv_ing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        order_lv_finish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void initData() {
//        orderfinish_empname.setText((String) map.get("empname"));
//        orderfinish_tv_task.setText((String) map.get("taskname"));
//        orderfinish_tv_text.setText((String) map.get("text"));
//        orderfinish_tv_dis.setText((String) map.get("distance"));
//        orderfinish_tv_money.setText((String) map.get("money"));
        for (int i = 0; i < 5; i++) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("empname", "快递员" + i);
//            map.put("taskname", "任务" + i);
//            map.put("text", "备注" + i);
//            map.put("distance", 1.0);
//            map.put("money", 1.0);
            BeanOrder beanOrder = new BeanOrder();
            beanOrder.setEmpAccount("kuaidiyuan" + i);
            beanOrder.setOrderName("任务" + i);
            beanOrder.setOrderText("备注" + i);
            beanOrder.setDistence(1.0);
            beanOrder.setMoney(1.0);
            list.add(beanOrder);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title1_back:
                finish();
                break;
        }
    }
}
