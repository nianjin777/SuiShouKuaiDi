package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class MyOrderActivity extends AppCompatActivity {

    private View view;
    private View ordewr_title4;
    private TextView title1_tv;
    private ListView order_lv_notfin;
    private ListView order_lv_wiateval;
    private ListView order_lv_haveeval;
    private ListView order_lv_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        findViewById();
    }

    public void findViewById() {
        ordewr_title4 = findViewById(R.id.ordewr_title4);
        title1_tv = (TextView) ordewr_title4.findViewById(R.id.title1_tv);
        title1_tv.setText("我的订单");
        order_lv_notfin = (ListView) view.findViewById(R.id.order_lv_notfin);
        order_lv_wiateval = (ListView) view.findViewById(R.id.order_lv_wiateval);
        order_lv_haveeval = (ListView) view.findViewById(R.id.order_lv_haveeval);
        order_lv_history = (ListView) view.findViewById(R.id.order_lv_history);
    }

}
