package com.edu.zucc.wmhxa.kuaishou.activity.order.issue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class ThingsAddActivity extends Activity implements View.OnClickListener {

    private EditText add_et_setname;
    private Button add_bt_setplace;
    private EditText add_et_setmoney;
    private Button add_bt_cancel;
    private Button add_bt_add;
    private ImageView title1_back;
    private EditText add_et_setnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_add);

        findViewById();
        setListener();

    }

    private void findViewById() {
        View add_title1 = findViewById(R.id.add_title1);
        TextView title1_tv = (TextView) add_title1.findViewById(R.id.title1_tv);
        title1_back = (ImageView) add_title1.findViewById(R.id.title1_back);
        title1_tv.setText("添加物品");

        add_et_setnumber = (EditText) findViewById(R.id.add_et_setnumber);
        add_et_setname = (EditText) findViewById(R.id.add_et_setname);
        add_bt_setplace = (Button) findViewById(R.id.add_bt_setplace);
        add_et_setmoney = (EditText) findViewById(R.id.add_et_setmoney);
        add_bt_cancel = (Button) findViewById(R.id.add_bt_cancel);
        add_bt_add = (Button) findViewById(R.id.add_bt_add);
    }

    private void setListener() {
        title1_back.setOnClickListener(this);
        add_bt_cancel.setOnClickListener(this);
        add_bt_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title1_back:
                finish();
                break;
            case R.id.add_bt_cancel:
                //TODO 执行添加操作
                break;
            case R.id.add_bt_add:
                finish();
                break;
        }
    }
}
