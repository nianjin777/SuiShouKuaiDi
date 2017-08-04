package com.edu.wmhxa.sskd.activity.comm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanUser;


public class DetailFriendActivity extends Activity {

    private ListView det_title;
    private ImageView det_im_headpic;
    private TextView det_tv_name;
    private TextView det_tv_sex;
    private TextView det_tv_level;
    private Button det_bt_sendmes;
    private ImageView back;
    private BeanUser friendUser;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_friend);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        friendUser = MsgCenter.friendList.get(position);

        findViewById();
        setListener();
    }

    private void findViewById() {
        View det_title = findViewById(R.id.det_title);
        TextView title1_tv = (TextView) det_title.findViewById(R.id.title1_tv);
        title1_tv.setText("发送消息");

        //数据重现
        det_im_headpic = (ImageView) findViewById(R.id.det_im_headpic);
        det_tv_name = (TextView) findViewById(R.id.det_tv_name);
        det_tv_name.setText(friendUser.getName());
        det_tv_sex = (TextView) findViewById(R.id.det_tv_sex);
        det_tv_sex.setText(friendUser.getSex());
        det_tv_level = (TextView) findViewById(R.id.det_tv_level);
        det_tv_level.setText(String.valueOf(friendUser.getGood()));
        det_bt_sendmes = (Button) findViewById(R.id.det_bt_sendmes);
    }

    private void setListener() {
        det_bt_sendmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailFriendActivity.this, MesFriActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("type", "friend");
                startActivity(intent);
            }
        });
    }
}
