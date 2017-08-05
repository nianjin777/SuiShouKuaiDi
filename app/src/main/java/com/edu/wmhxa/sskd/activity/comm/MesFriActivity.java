package com.edu.wmhxa.sskd.activity.comm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanUser;

import java.util.List;

public class MesFriActivity extends Activity {

    private ListView mesfri_title;
    private ListView mesfri_lv_meswindow;
    private int position;
    private String type;
    private BeanUser msgUser;
    private EditText mesfri_et_message;
    private Button mesfri_bt_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_fri);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        type = intent.getStringExtra("type");
        if (type.equals("order")) {
            msgUser = MsgCenter.orderMsgList.get(position);
        } else if (type.equals("friend")) {
            msgUser = MsgCenter.friendList.get(position);
        } else {
            finish();
        }
        findViewById();
    }

    private void findViewById() {
        View mesfri_title = findViewById(R.id.mesfri_title);
        TextView title1_tv = (TextView) mesfri_title.findViewById(R.id.title1_tv);
        title1_tv.setText(msgUser.getName());
        mesfri_lv_meswindow = (ListView) findViewById(R.id.mesfri_lv_meswindow);
        mesfri_et_message = (EditText)findViewById(R.id.mesfri_et_message);
        mesfri_bt_send = (Button) findViewById(R.id.mesfri_bt_send);
    }
}
