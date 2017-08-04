package com.edu.wmhxa.sskd.activity.setting;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;

public class EditActivity extends Activity {

    private ListView edit_title;
    private EditText edit_et_phone;
    private EditText edit_et_email;
    private TextView edit_tv_phone;
    private TextView edit_tv_email;
    private TextView edit_bt_save;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViewById();
        setListener();
    }

    private void findViewById() {
        View edit_title = findViewById(R.id.edit_title);
        TextView title1_tv = (TextView) edit_title.findViewById(R.id.title1_tv);
        back = (ImageView) edit_title.findViewById(R.id.title1_back);
        title1_tv.setText("修改信息");
        edit_et_phone = (EditText) findViewById(R.id.edit_et_phone);
        edit_et_email = (EditText) findViewById(R.id.edit_et_email);
        edit_tv_phone = (TextView) findViewById(R.id.edit_tv_phone);
        edit_tv_email = (TextView) findViewById(R.id.edit_tv_email);
        edit_bt_save = (TextView) findViewById(R.id.edit_bt_save);
    }

    private void setListener() {
        edit_bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = edit_et_phone.getText().toString();
                final String email = edit_et_email.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MsgCenter.getInstanceMsgCenter().changeEmailAndPhone(email, phone);
                    }
                }).start();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
