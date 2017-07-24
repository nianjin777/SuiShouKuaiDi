package com.edu.zucc.wmhxa.kuaishou.activity.order;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class MyOrderActivity extends Activity implements View.OnClickListener {

    private View ordewr_title1;
    private TextView title1_tv;
    private ListView order_lv_notfin;
    private ListView order_lv_wiateval;
    private ListView order_lv_haveeval;
    private ListView order_lv_history;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        
        findViewById();
        setListener();
    }

    public void findViewById() {
        ordewr_title1 = findViewById(R.id.ordewr_title1);
        title1_tv = (TextView) ordewr_title1.findViewById(R.id.title1_tv);
        title1_tv.setText("我的订单");
        back = (ImageView) ordewr_title1.findViewById(R.id.title1_back);
        order_lv_notfin = (ListView) findViewById(R.id.order_lv_notfin);
        order_lv_wiateval = (ListView) findViewById(R.id.order_lv_wiateval);
        order_lv_haveeval = (ListView) findViewById(R.id.order_lv_haveeval);
        order_lv_history = (ListView) findViewById(R.id.order_lv_history);
    }

    private void setListener() {
        back.setOnClickListener(this);
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
