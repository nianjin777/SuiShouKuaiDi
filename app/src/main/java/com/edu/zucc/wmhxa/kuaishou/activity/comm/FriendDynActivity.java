package com.edu.zucc.wmhxa.kuaishou.activity.comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class FriendDynActivity extends AppCompatActivity {

    private View view;
    private ImageView fridyn_tv_backgroundpic;
    private ImageView fdyn_iv_headpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_dyn);
        findViewById();
    }

    public void findViewById() {
        fridyn_tv_backgroundpic = (ImageView) view.findViewById(R.id.fridyn_tv_backgroundpic);
        fdyn_iv_headpic = (ImageView) view.findViewById(R.id.fdyn_iv_headpic);
    }
}
