package com.edu.wmhxa.sskd.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.model.BeanUser;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import java.util.List;

import static com.baidu.location.d.j.ad;


public class SeeOrderActivity extends Activity {

    private TextView see_tv_name;
    private TextView see_tv_phone;
    private TextView see_tv_taskname;
    private ListView see_lv_thing;
    private TextView see_tv_text;
    private TextView see_tv_money;
    private TextView see_tv_totle;
    private View see_title;
    private BeanOrder beanOrder;
    private TextView see_tv_address;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeorder);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        int positon = intent.getIntExtra("position", -1);
        if (positon == -1) {
            finish();
        }
        if (type.equals("wait")) {
            beanOrder = MsgCenter.waitOrderList.get(positon);
        } else if (type.equals("ing")) {
            if (positon >= MsgCenter.empOrderList.size()) {
                positon -= MsgCenter.empOrderList.size();
                beanOrder = MsgCenter.ingOrderList.get(positon);
            } else {
                if (MsgCenter.empOrderList.size() != 0) {
                    beanOrder = MsgCenter.empOrderList.get(positon);
                } else {
                    beanOrder = MsgCenter.ingOrderList.get(positon);
                }
            }
        } else if (type.equals("finish")) {
            beanOrder = MsgCenter.finishOrderList.get(positon);
        }
        findViewById();
        setListener();

    }

    public void findViewById() {
        see_title = findViewById(R.id.see_title);
        TextView title1_tv = (TextView) see_title.findViewById(R.id.title1_tv);
        back = (ImageView) see_title.findViewById(R.id.title1_back);
        title1_tv.setText("订单详情");
        see_tv_name = (TextView) findViewById(R.id.see_tv_name);
        see_tv_phone = (TextView) findViewById(R.id.see_tv_phone);
        see_tv_address = (TextView) findViewById(R.id.see_tv_address);
        see_tv_taskname = (TextView) findViewById(R.id.see_tv_taskname);
        see_lv_thing = (ListView) findViewById(R.id.see_lv_thing);
        see_tv_text = (TextView) findViewById(R.id.see_tv_text);
        see_tv_money = (TextView) findViewById(R.id.see_tv_money);
        see_tv_totle = (TextView) findViewById(R.id.see_tv_totle);

        //数据重现
        BeanAddress address = beanOrder.getAddress();
        see_tv_name.setText(address.getName());
        see_tv_phone.setText(address.getPhone());
        see_tv_address.setText(address.getLocation() + "\n" + address.getInfo());
        see_tv_taskname.setText(beanOrder.getOrderName());
        List<BeanThing> thingList = beanOrder.getThingList();
        see_lv_thing.setAdapter(new ThingListAdapter(getApplicationContext(), thingList));
        see_tv_text.setText(beanOrder.getOrderText());
        see_tv_money.setText(String.valueOf(beanOrder.getBounty()));
        int totle = 0;
        for (int i = 0; i < thingList.size(); i++) {
            totle += thingList.get(i).getMoney();
        }
        totle += beanOrder.getBounty();
        see_tv_totle.setText(String.valueOf(totle));

    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
