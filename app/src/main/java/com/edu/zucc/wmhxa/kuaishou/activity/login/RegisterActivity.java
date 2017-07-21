package com.edu.zucc.wmhxa.kuaishou.activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.util.SysApplication;

import static android.R.id.button1;

public class RegisterActivity extends AppCompatActivity {

    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SysApplication.getInstance().addActivity(this);

        findViewById();
        setListener();

    }

    //获取控件
    private void findViewById() {
        regist = (Button) findViewById(R.id.register_bt_return);
    }
    //设置监听
    private void setListener() {
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
