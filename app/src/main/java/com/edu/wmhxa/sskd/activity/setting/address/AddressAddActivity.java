package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;


public class AddressAddActivity extends Activity implements View.OnClickListener {

    private View view;
    private EditText add_et_savename;
    private EditText add_et_savephone;
    private EditText add_et_saveplace;
    private EditText add_et_savereplace;
    private Button addaddress_bt_default;
    private ImageView back;
    private Button title3_bt;

    private boolean isDefault = false;

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
        addaddress_bt_default = (Button) findViewById(R.id.addaddress_bt_default);
    }

    private void setListener() {
        title3_bt.setOnClickListener(this);
        back.setOnClickListener(this);
        addaddress_bt_default.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title3_bt:
                BeanAddress info = getInfo();
                boolean result = MsgCenter.getInstanceMsgCenter().addAddress(info);
                if (result) {
                    Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                    break;
                }
                finish();
                break;
            case R.id.title3_back:
                finish();
                break;
            case R.id.addaddress_bt_default:
                if (isDefault) {
                    isDefault = false;
                    addaddress_bt_default.setBackgroundColor(Color.WHITE);
                } else {
                    isDefault = true;
                    addaddress_bt_default.setBackgroundColor(Color.GRAY);
                }
                break;
        }
    }

    private BeanAddress getInfo() {
        BeanAddress beanAddress = new BeanAddress();
        beanAddress.setName(add_et_savename.getText().toString());
        beanAddress.setPhone(add_et_savephone.getText().toString());
        beanAddress.setLocation(add_et_saveplace.getText().toString());
        beanAddress.setInfo(add_et_savereplace.getText().toString());
        beanAddress.setAddrDefault(isDefault);

        return beanAddress;
    }
}
