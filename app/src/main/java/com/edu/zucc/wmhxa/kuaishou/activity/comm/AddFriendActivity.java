package com.edu.zucc.wmhxa.kuaishou.activity.comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class AddFriendActivity extends AppCompatActivity {

    private ListView addfir_title;
    private View view;
    private ImageView addfri_im_headpic;
    private TextView addfri_tv_name;
    private TextView addfri_tv_sex;
    private TextView addfri_tv_level;
    private Button addfri_bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        findViewById();

    }
    private void findViewById(){
        View addfir_title = findViewById(R.id.addfir_title);
        TextView title1_tv = (TextView) addfir_title.findViewById(R.id.title1_tv);
        title1_tv.setText("申请好友");
        addfri_im_headpic=(ImageView)view.findViewById(R.id.addfri_im_headpic);
        addfri_tv_name=(TextView)view.findViewById(R.id.addfri_tv_name);
        addfri_tv_sex=(TextView)view.findViewById(R.id.addfri_tv_sex);
        addfri_tv_level=(TextView)view.findViewById(R.id.addfri_tv_level);
        addfri_bt_add=(Button)view.findViewById(R.id.addfri_bt_add);
    }
}
