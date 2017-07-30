package com.edu.zucc.wmhxa.kuaishou.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.model.BeanUser;
import com.edu.zucc.wmhxa.kuaishou.util.SysApplication;

import static android.R.id.button1;
import static android.R.id.closeButton;

public class RegisterActivity extends Activity {

    private Button regist;
    private EditText reg_et_zhanghao;
    private EditText reg_et_mima;
    private EditText reg_et_remima;
    private EditText reg_et_name;
    private RadioGroup reg_rg_sexgroup;
    private RadioButton reg_rb_sexman;
    private RadioButton reg_rb_sexwomen;
    private EditText reg_et_idcard;
    private EditText reg_et_phone;
    private EditText reg_et_email;
    private Button register_bt_new;
    private BeanUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SysApplication.getInstance().addActivity(this);

        findViewById();

        setListener();

    }


    private void onCheckedChanged(RadioGroup reg_rg_sexgroup,int checkedID){
        if(reg_rb_sexman.getId()==checkedID)
            user.getSex(reg_rb_sexman.getText().toString());
        else if (reg_rb_sexwomen.getId()==checkedID)
            user.getSex(reg_rb_sexwomen.getText().toString());
    }


    //获取控件
    private BeanUser findViewById() {
        regist = (Button) findViewById(R.id.register_bt_return);
        reg_et_zhanghao = (EditText) findViewById(R.id.reg_et_zhanghao);
        user.getUsername(reg_et_zhanghao.getText().toString());
        reg_et_mima = (EditText) findViewById(R.id.reg_et_mima);
        user.getPassword(reg_et_mima.getText().toString());
        reg_et_remima = (EditText) findViewById(R.id.reg_et_remima);
        reg_et_name = (EditText) findViewById(R.id.reg_et_name);
        user.getName(reg_et_name.getText().toString());
        reg_rg_sexgroup = (RadioGroup) findViewById(R.id.reg_rg_sexgroup);
        reg_rb_sexman = (RadioButton) findViewById(R.id.reg_rb_sexman);
        reg_rb_sexwomen = (RadioButton) findViewById(R.id.reg_rb_sexwomen);

        reg_et_idcard = (EditText) findViewById(R.id.reg_et_idcard);
        user.getID(reg_et_idcard.getText().toString());
        reg_et_phone = (EditText) findViewById(R.id.reg_et_phone);
        user.getPhone(reg_et_phone.getText().toString());
        reg_et_email = (EditText) findViewById(R.id.reg_et_email);
        user.getEmail(reg_et_email.getText().toString());
        register_bt_new = (Button) findViewById(R.id.register_bt_new);
        return user;
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
