package com.edu.zucc.wmhxa.kuaishou.activity.comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class SendDynActivity extends AppCompatActivity {

    private View view;
    private Button senddyn_bt_exit;
    private Button senddyn_bt_send;
    private TextView senddyn_tv_city;
    private EditText senddyn_et_title;
    private EditText senddyn_et_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_dyn);
        findViewById();
    }

    public void findViewById() {
        senddyn_bt_exit = (Button) view.findViewById(R.id.senddyn_bt_exit);
        senddyn_bt_send = (Button) view.findViewById(R.id.senddyn_bt_send);
        senddyn_tv_city = (TextView) view.findViewById(R.id.senddyn_tv_city);
        senddyn_et_title = (EditText) view.findViewById(R.id.senddyn_et_title);
        senddyn_et_text = (EditText) view.findViewById(R.id.senddyn_et_text);
    }

}
