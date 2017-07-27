package com.edu.zucc.wmhxa.kuaishou.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.activity.home.HomeActivity;
import com.edu.zucc.wmhxa.kuaishou.activity.login.LoginActivity;
import com.edu.zucc.wmhxa.kuaishou.control.MsgCenter;
import com.edu.zucc.wmhxa.kuaishou.model.BeanUser;
import com.edu.zucc.wmhxa.kuaishou.util.SysApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/7/19.
 */

public class WelcomeActivity extends Activity {

    //进入界面显示时间
    private final static int SHOWTIME = 1;

    private Intent intent;
    private final Timer timer = new Timer();
    private TimerTask task;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 要做的事情
            if (instanceMsgCenter.beanUser != null) {
                intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            } else {
                intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };
    private MsgCenter instanceMsgCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        SysApplication.getInstance().addActivity(this);

        //初始化消息中心
        instanceMsgCenter = MsgCenter.getInstanceMsgCenter();

        //初始化计时器任务
        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(task, SHOWTIME * 1000);
    }
}
