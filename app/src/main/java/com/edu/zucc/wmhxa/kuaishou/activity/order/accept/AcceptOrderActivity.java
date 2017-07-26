package com.edu.zucc.wmhxa.kuaishou.activity.order.accept;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.model.BeanThing;
import com.edu.zucc.wmhxa.kuaishou.util.adapter.ThingListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcceptOrderActivity extends Activity implements View.OnClickListener {
    private TextView accept_tv_taskname;
    private ListView accept_lv_thing;
    private TextView accrpt_tv_text;
    private TextView accept_tv_money;
    private TextView accept_tv_totle;
    private Button accept_bt_back;
    private Button accept_bt_accept;
    private Map<String, Object> info;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept);
        //拿到选择任务的信息Map
        Bundle extras = getIntent().getExtras();
        info = (HashMap<String, Object>) extras.get("info");
        findViewById();
        setListener();
    }

    public void findViewById() {
        //设置标题
        View title = findViewById(R.id.accept_title);
        back = (ImageView) title.findViewById(R.id.title1_back);
        TextView title1_tv = (TextView) title.findViewById(R.id.title1_tv);
        title1_tv.setText("订单详情");

        List<BeanThing> things = (List<BeanThing>) info.get("things");
        double totle = (Double) info.get("money");
        for (BeanThing beanThing : things) {
            totle += beanThing.getMoney() * beanThing.getNumber();
        }

        //任务名
        accept_tv_taskname = (TextView) findViewById(R.id.accept_tv_taskname);
        accept_tv_taskname.setText((String) info.get("taskname"));
        //物品清单
        accept_lv_thing = (ListView) findViewById(R.id.accept_lv_thing);
        accept_lv_thing.setAdapter(new ThingListAdapter(getApplicationContext(), things));
        //备注
        accrpt_tv_text = (TextView) findViewById(R.id.accrpt_tv_text);
        accrpt_tv_text.setText((String) info.get("text"));
        //金额
        accept_tv_money = (TextView) findViewById(R.id.accept_tv_money);
        accept_tv_money.setText(String.valueOf(info.get("money")));
        //总价
        accept_tv_totle = (TextView) findViewById(R.id.accept_tv_totle);
        accept_tv_totle.setText(String.valueOf(totle));

        accept_bt_back = (Button) findViewById(R.id.accept_bt_back);
        accept_bt_accept = (Button) findViewById(R.id.accept_bt_accept);
    }

    public void setListener() {
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
                break;
            case R.id.title1_back:
                finish();
                break;
        }
    }
}
