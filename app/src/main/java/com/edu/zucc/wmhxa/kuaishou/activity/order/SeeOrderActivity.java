package com.edu.zucc.wmhxa.kuaishou.activity.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class SeeOrderActivity extends AppCompatActivity {

    private View view;
    private TextView see_tv_name;
    private TextView see_tv_phone;
    private TextView see_tv_taskname;
    private ListView see_lv_thing;
    private TextView see_tv_text;
    private TextView see_tv_money;
    private TextView see_tv_totle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeorder);
        findViewById();
    }
    public void findViewById(){
        see_tv_name=(TextView)view.findViewById(R.id.see_tv_name);
        see_tv_phone=(TextView)view.findViewById(R.id.see_tv_phone);
        see_tv_taskname=(TextView)view.findViewById(R.id.see_tv_taskname);
        see_lv_thing=(ListView)view.findViewById(R.id.see_lv_thing);
        see_tv_text=(TextView)view.findViewById(R.id.see_tv_text);
        see_tv_money=(TextView)view.findViewById(R.id.see_tv_money);
        see_tv_totle=(TextView)view.findViewById(R.id.see_tv_totle);
    }
}
