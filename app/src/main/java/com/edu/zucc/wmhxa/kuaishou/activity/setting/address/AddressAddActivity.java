package com.edu.zucc.wmhxa.kuaishou.activity.setting.address;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

import org.w3c.dom.Text;


public class AddressAddActivity extends Activity implements View.OnClickListener {

    private View view;
    private EditText add_et_savename;
    private EditText add_et_savephone;
    private EditText add_et_saveplace;
    private EditText add_et_savereplace;
    private Button addaddress_bt_del;
    private ImageView back;
    private Button title3_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);

        findViewById();
        setListener();

    }

    private void findViewById() {
        View title = findViewById(R.id.addaddress_title);
        TextView title_tv = (TextView) title.findViewById(R.id.title3_tv);
        title_tv.setText("添加地址");
        back = (ImageView) title.findViewById(R.id.title3_back);
        title3_bt = (Button) title.findViewById(R.id.title3_bt);
        title3_bt.setText("完成");

        add_et_savename = (EditText) findViewById(R.id.add_et_savename);
        add_et_savephone = (EditText) findViewById(R.id.add_et_savephone);
        add_et_saveplace = (EditText) findViewById(R.id.add_et_saveplace);
        add_et_savereplace = (EditText) findViewById(R.id.add_et_savereplace);
        addaddress_bt_del = (Button) findViewById(R.id.addaddress_bt_del);
    }

    private void setListener() {
        title3_bt.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title3_bt:
                finish();
                break;
            case R.id.title3_back:
                //TODO 保存操作
                finish();
                break;
        }
    }
}
