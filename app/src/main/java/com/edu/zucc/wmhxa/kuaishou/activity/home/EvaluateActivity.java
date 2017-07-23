package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class EvaluateActivity extends AppCompatActivity {

    private View view;
    private Button eva_tv_save;
    private TextView eva_name;
    private TextView eva_state;
    private TextView eva_tv_task;
    private TextView eva_tv_dis;
    private TextView eva_tv_text;
    private TextView eva_tv_money;
    private EditText eva_et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        findViewById();
    }
    public void findViewById(){
        eva_tv_save=(Button) view.findViewById(R.id.eva_tv_save);
        eva_name = (TextView) view.findViewById(R.id.eva_name) ;
        eva_state= (TextView) view.findViewById(R.id.eva_state);
        eva_tv_task =(TextView) view.findViewById(R.id.eva_tv_task);
        eva_tv_dis= (TextView) view.findViewById(R.id.eva_tv_dis);
        eva_tv_text= (TextView) view.findViewById(R.id.eva_tv_text);
        eva_tv_money =(TextView) view.findViewById(R.id.eva_tv_money);
        eva_et_text=(EditText) view.findViewById(R.id.eva_et_text);
    }
}
