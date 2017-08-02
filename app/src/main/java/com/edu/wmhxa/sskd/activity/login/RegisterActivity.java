package com.edu.wmhxa.sskd.activity.login;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanUser;
import com.edu.wmhxa.sskd.util.SysApplication;

public class RegisterActivity extends Activity {

    private Button regist;
    private EditText reg_et_zhanghao;
    private EditText reg_et_mima;
    private EditText reg_et_remima;
    private EditText reg_et_name;
    private RadioGroup reg_rg_sexgroup;
    private RadioButton checkButton;
    private EditText reg_et_idcard;
    private EditText reg_et_phone;
    private EditText reg_et_email;
    private Button register_bt_new;
    private BeanUser user;

    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SysApplication.getInstance().addActivity(this);

        findViewById();

        setListener();

    }

    //获取控件
    private BeanUser findViewById() {
        regist = (Button) findViewById(R.id.register_bt_return);
        reg_et_zhanghao = (EditText) findViewById(R.id.reg_et_zhanghao);
        reg_et_mima = (EditText) findViewById(R.id.reg_et_mima);
        reg_et_remima = (EditText) findViewById(R.id.reg_et_remima);
        reg_et_name = (EditText) findViewById(R.id.reg_et_name);
        reg_rg_sexgroup = (RadioGroup) findViewById(R.id.reg_rg_sexgroup);
        reg_et_idcard = (EditText) findViewById(R.id.reg_et_idcard);
        reg_et_phone = (EditText) findViewById(R.id.reg_et_phone);
        reg_et_email = (EditText) findViewById(R.id.reg_et_email);
        register_bt_new = (Button) findViewById(R.id.register_bt_new);
        return user;
    }

    //设置监听
    private void setListener() {
        register_bt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = getData();
                boolean result = MsgCenter.getInstanceMsgCenter().regist(user);
                if (result) {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
        reg_rg_sexgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                checkButton = (RadioButton) group.findViewById(checkedId);
                sex = (String) checkButton.getText();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private BeanUser getData() {
        BeanUser beanUser = new BeanUser();
        beanUser.setUsername(reg_et_zhanghao.getText().toString());
        beanUser.setPassword(reg_et_mima.getText().toString());
        beanUser.setName(reg_et_name.getText().toString());
        beanUser.setID(reg_et_idcard.getText().toString());
        beanUser.setPhone(reg_et_phone.getText().toString());
        beanUser.setEmail(reg_et_email.getText().toString());
        beanUser.setSex(sex);
        return beanUser;
    }

}
