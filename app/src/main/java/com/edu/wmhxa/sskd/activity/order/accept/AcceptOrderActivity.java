package com.edu.wmhxa.sskd.activity.order.accept;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
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
    private Map<String, Object> order;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept);
        //拿到选择任务的信息Map
        Bundle extras = getIntent().getExtras();
        order = (HashMap<String, Object>) extras.get("info");
        findViewById();
        setListener();
    }

    public void findViewById() {
        //设置标题
        View title = findViewById(R.id.accept_title);
        back = (ImageView) title.findViewById(R.id.title1_back);
        TextView title1_tv = (TextView) title.findViewById(R.id.title1_tv);
        title1_tv.setText("订单详情");

        List<BeanThing> things = (List<BeanThing>) order.get("thinglist");
        double totle = (Double) order.get("orderbounty");
        for (BeanThing beanThing : things) {
            totle += beanThing.getMoney();
        }

        //大致地址
        accept_tv_addr = (TextView) findViewById(R.id.accept_tv_addr);
        accept_tv_addr.setText((String) order.get("orderaddress"));
        //任务名
        accept_tv_taskname = (TextView) findViewById(R.id.accept_tv_taskname);
        accept_tv_taskname.setText((String) order.get("ordername"));
        //物品清单
        accept_lv_thing = (ListView) findViewById(R.id.accept_lv_thing);
        accept_lv_thing.setAdapter(new ThingListAdapter(getApplicationContext(), things));
        ListViewUtil.setListViewHeightBasedOnChildren(accept_lv_thing);
        //备注
        accrpt_tv_text = (TextView) findViewById(R.id.accrpt_tv_text);
        accrpt_tv_text.setText((String) order.get("ordertext"));
        //金额
        accept_tv_money = (TextView) findViewById(R.id.accept_tv_money);
        accept_tv_money.setText(String.valueOf(order.get("orderbounty")));
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
                break;
            case R.id.title1_back:
                finish();
                break;
        }
    }
}
