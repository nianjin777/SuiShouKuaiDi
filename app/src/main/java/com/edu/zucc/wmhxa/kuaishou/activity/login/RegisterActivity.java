package com.edu.zucc.wmhxa.kuaishou.activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.util.SysApplication;

import static android.R.id.button1;
import static android.R.id.closeButton;

public class RegisterActivity extends AppCompatActivity {

    private Button regist;
    private EditText reg_et_zhanghao;
    private EditText reg_et_mima;
    private EditText reg_et_remima;
    private EditText reg_et_name;
    private RadioButton reg_rb_sexman;
    private RadioButton ref_rb_sexwomen;
    private EditText reg_et_idcard;
    private EditText reg_et_phone;
    private EditText reg_et_email;
    private Button register_bt_new;

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
        reg_et_zhanghao=(EditText) findViewById(R.id.reg_et_zhanghao);
        reg_et_mima=(EditText) findViewById(R.id.reg_et_mima);
        reg_et_remima=(EditText) findViewById(R.id.reg_et_remima);
        reg_et_name=(EditText) findViewById(R.id.reg_et_name);
        reg_rb_sexman=(RadioButton) findViewById(R.id.reg_rb_sexman);
        ref_rb_sexwomen=(RadioButton) findViewById(R.id.ref_rb_sexwomen);
        reg_et_idcard=(EditText) findViewById(R.id.reg_et_idcard);
        reg_et_phone=(EditText) findViewById(R.id.reg_et_phone);
        reg_et_email=(EditText) findViewById(R.id.reg_et_email);
        register_bt_new = (Button) findViewById(R.id.register_bt_new);
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
