package com.edu.wmhxa.sskd.activity.order.evaluate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;


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
    private Button title3_bt;
    private ImageView title3_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        findViewById();
    }

    public void findViewById() {
        eva_name = (TextView) view.findViewById(R.id.eva_name);
        eva_state = (TextView) view.findViewById(R.id.eva_state);
        eva_tv_task = (TextView) view.findViewById(R.id.eva_tv_task);
        eva_tv_dis = (TextView) view.findViewById(R.id.eva_tv_dis);
        eva_tv_text = (TextView) view.findViewById(R.id.eva_tv_text);
        eva_tv_money = (TextView) view.findViewById(R.id.eva_tv_money);
        eva_et_text = (EditText) view.findViewById(R.id.eva_et_text);

        View eval_title = findViewById(R.id.eval_title);
        title3_back = (ImageView) eval_title.findViewById(R.id.title3_back);
        TextView title3_tv = (TextView) eval_title.findViewById(R.id.title3_tv);
        title3_bt = (Button) eval_title.findViewById(R.id.title3_bt);
        title3_bt.setText("发布");
        title3_tv.setText("订单评价");
    }
}
