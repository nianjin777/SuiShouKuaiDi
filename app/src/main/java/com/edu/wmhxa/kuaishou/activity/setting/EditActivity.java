package com.edu.wmhxa.kuaishou.activity.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.kuaishou.R;

public class EditActivity extends AppCompatActivity {

    private ListView edit_title;
    private EditText edit_et_phone;
    private EditText edit_et_email;
    private TextView edit_tv_phone;
    private TextView edit_tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViewById();
    }
    private void findViewById() {
        View edit_title = findViewById(R.id.edit_title);
        TextView title1_tv = (TextView) edit_title.findViewById(R.id.title1_tv);
        title1_tv.setText("修改信息");
        edit_et_phone=(EditText)findViewById(R.id.edit_et_phone);
        edit_et_email=(EditText)findViewById(R.id.edit_et_email);
        edit_tv_phone=(TextView) findViewById(R.id.edit_tv_phone);
        edit_tv_email=(TextView) findViewById(R.id.edit_tv_email);
    }
}
