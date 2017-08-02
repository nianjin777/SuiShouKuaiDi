package com.edu.wmhxa.sskd.activity.comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;


public class DetailFriendActivity extends AppCompatActivity {

    private ListView det_title;
    private ImageView det_im_headpic;
    private TextView det_tv_name;
    private TextView det_tv_sex;
    private TextView det_tv_level;
    private Button det_bt_sendmes;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_friend);
        findViewById();
    }
    private void findViewById() {
        View det_title = findViewById(R.id.det_title);
        TextView title1_tv = (TextView) det_title.findViewById(R.id.title1_tv);
        title1_tv.setText("发送消息");
        det_im_headpic=(ImageView)findViewById(R.id.det_im_headpic);
        det_tv_name=(TextView)findViewById(R.id.det_tv_name);
        det_tv_sex=(TextView)findViewById(R.id.det_tv_sex);
        det_tv_level=(TextView)findViewById(R.id.det_tv_level);
        det_bt_sendmes=(Button)findViewById(R.id.det_bt_sendmes);

    }
}
