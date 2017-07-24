package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class AcceptOrderActivity extends AppCompatActivity {
    private View view;
    private TextView accept_tv_name;
    private TextView accept_tv_phone;
    private TextView accept_tv_taskname;
    private ListView accept_lv_thing;
    private TextView accrpt_tv_text;
    private TextView accept_tv_money;
    private TextView accept_tv_totle;
    private Button accept_bt_back;
    private Button accept_bt_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_order);
        findViewById();
    }
    public void findViewById(){
        accept_tv_name =(TextView) view.findViewById(R.id.accept_tv_name);
        accept_tv_phone =(TextView) view.findViewById(R.id.accept_tv_phone);
        accept_tv_taskname=(TextView) view.findViewById(R.id.accept_tv_taskname);
        accept_lv_thing=(ListView)view.findViewById(R.id.accept_lv_thing);
        accrpt_tv_text=(TextView) view.findViewById(R.id.accrpt_tv_text);
        accept_tv_money = (TextView) view.findViewById(R.id.accept_tv_money);
        accept_tv_totle=(TextView)view.findViewById(R.id.accept_tv_totle);
        accept_bt_back=(Button) view.findViewById(R.id.accept_bt_back);
        accept_bt_accept=(Button) view.findViewById(R.id.accept_bt_accept);
    }
}
