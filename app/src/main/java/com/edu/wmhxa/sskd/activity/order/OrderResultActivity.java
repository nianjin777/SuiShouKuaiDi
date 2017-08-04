package com.edu.wmhxa.sskd.activity.order;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;


/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderResultActivity extends Activity {

    private TextView orderresult_tv_name;
    private TextView orderresult_tv_phone;
    private TextView orderresult_tv_addr;
    private TextView orderresult_tv_taskname;
    private ListView orderresult_lv;
    private TextView orderresult_tv_text;
    private View orderresult_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderresult);

        findViewById();

    }

    private void findViewById() {
        orderresult_title = findViewById(R.id.orderresult_title);
        TextView title1_tv = (TextView) orderresult_title.findViewById(R.id.title1_tv);
        title1_tv.setText("订单详情");
        orderresult_tv_name = (TextView) findViewById(R.id.orderresult_tv_name);
        orderresult_tv_phone = (TextView) findViewById(R.id.orderresult_tv_phone);
        orderresult_tv_addr = (TextView) findViewById(R.id.orderresult_tv_addr);
        orderresult_tv_taskname = (TextView) findViewById(R.id.orderresult_tv_taskname);
        orderresult_tv_text = (TextView) findViewById(R.id.orderresult_tv_text);
        orderresult_lv = (ListView) findViewById(R.id.orderresult_lv);
//        orderresult_lv.setAdapter(new ThingListAdapter(getApplicationContext(),list));

    }

}
