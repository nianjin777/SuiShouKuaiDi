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
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                }
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
        String username = reg_et_zhanghao.getText().toString();
        String password = reg_et_mima.getText().toString();
        String rePassword = reg_et_remima.getText().toString();
        String name = reg_et_name.getText().toString();
        String shenfenzheng = reg_et_idcard.getText().toString();
        String phone = reg_et_phone.getText().toString();
        String email = reg_et_email.getText().toString();
        //TODO 这这里写校验 写在sex判断上面

        if (username == null || username.length()<8){
            Toast.makeText(getApplicationContext(),"账号不小于8位",Toast.LENGTH_SHORT).show();
            return null;
        }
        if (password == null || password.length()<8){
            Toast.makeText(getApplicationContext(),"密码不小于8位",Toast.LENGTH_SHORT).show();
            return null;
        }
        if (rePassword != password ){
            Toast.makeText(getApplicationContext(),"两次输入密码不同",Toast.LENGTH_SHORT).show();
            return null;
        }
        if ( name == null || name.length()>12){
            Toast.makeText(getApplicationContext(),"用户名小于6个汉字",Toast.LENGTH_SHORT).show();
            return null;
        }
        if ( shenfenzheng == null || shenfenzheng.length()!=18){
            Toast.makeText(getApplicationContext(),"身份证输入不正确",Toast.LENGTH_SHORT).show();
            return null;
        }
        if ( phone == null || phone.length()!=11){
            Toast.makeText(getApplicationContext(),"手机号输入不正确",Toast.LENGTH_SHORT).show();
            return null;
        }
        if (sex == null || sex.isEmpty()) {
            Toast.makeText(getApplicationContext(), "请选择性别", Toast.LENGTH_SHORT).show();
            return null;
        }

        beanUser.setUsername(username);
        beanUser.setPassword(password);
        beanUser.setName(name);
        beanUser.setID(shenfenzheng);
        beanUser.setPhone(phone);
        beanUser.setEmail(email);
        beanUser.setSex(sex);
        return beanUser;
    }

}
