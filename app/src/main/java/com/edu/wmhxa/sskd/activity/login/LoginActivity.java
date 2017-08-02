package com.edu.wmhxa.sskd.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.home.HomeActivity;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.util.SysApplication;

public class LoginActivity extends Activity {

    private long mExitTime;
    private Button register;
    private Button login_bt_login;
    private EditText login_et_username;
    private EditText login_et_password;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SysApplication.getInstance().addActivity(this);

        findViewById();
        setListerner();
    }

    //获取控件
    private void findViewById() {
        register = (Button) findViewById(R.id.login_bt_newuser);
        login_bt_login = (Button) findViewById(R.id.login_bt_login);
        login_et_username = (EditText) findViewById(R.id.login_et_username);
        login_et_password = (EditText) findViewById(R.id.login_et_password);
    }

    //设置监听
    private void setListerner() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login_bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                boolean result = MsgCenter.getInstanceMsgCenter().login(username, password);
                if (result) {
                    Toast.makeText(getApplicationContext(), "登陆成功\n正在为您跳转到主页", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "登陆失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {
        username = String.valueOf(login_et_username.getText());
        password = String.valueOf(login_et_password.getText());
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            SysApplication.getInstance().exit();
        }
    }

}
