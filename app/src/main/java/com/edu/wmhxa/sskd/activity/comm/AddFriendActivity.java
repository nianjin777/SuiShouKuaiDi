package com.edu.wmhxa.sskd.activity.comm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;


public class AddFriendActivity extends Activity {

    private ListView addfir_title;
    private ImageView addfri_im_headpic;
    private TextView addfri_tv_name;
    private TextView addfri_tv_sex;
    private TextView addfri_tv_level;
    private Button addfri_bt_add;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //TODO 向服务器发名字 搜索用户

        if (!false) {
            Intent result = new Intent();
            result.putExtra("result", false);
            setResult(10, result);
            finish();
        }

        findViewById();
        setListener();

    }

    private void findViewById() {
        View addfir_title = findViewById(R.id.addfir_title);
        TextView title1_tv = (TextView) addfir_title.findViewById(R.id.title1_tv);
        title1_tv.setText("申请好友");
        back = (ImageView) addfir_title.findViewById(R.id.title1_back);
        addfri_im_headpic = (ImageView) findViewById(R.id.addfri_im_headpic);
        addfri_tv_name = (TextView) findViewById(R.id.addfri_tv_name);
        addfri_tv_sex = (TextView) findViewById(R.id.addfri_tv_sex);
        addfri_tv_level = (TextView) findViewById(R.id.addfri_tv_level);
        addfri_bt_add = (Button) findViewById(R.id.addfri_bt_add);
    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addfri_bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 向服务器提交 好友申请
            }
        });
    }

}
