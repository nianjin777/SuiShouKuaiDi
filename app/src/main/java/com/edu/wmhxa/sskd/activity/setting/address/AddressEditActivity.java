package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;


public class AddressEditActivity extends Activity {

    private EditText edit_ev_savename;
    private EditText edit_ev_savephone;
    private EditText edit_ev_saveplace;
    private EditText edit_ev_savereplace;
    private Button eidtaddress_tv_save;
    private Button editaddress_bt_del;

    private int position;
    private BeanAddress beanAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        if (position == -1) {
            finish();
        }
        beanAddress = MsgCenter.addressList.get(position);

        findViewById();
        setListener();

    }

    private void findViewById() {
        edit_ev_savename = (EditText) findViewById(R.id.edit_ev_savename);
        edit_ev_savename.setText(beanAddress.getName());
        edit_ev_savephone = (EditText) findViewById(R.id.edit_ev_savephone);
        edit_ev_savephone.setText(beanAddress.getPhone());
        edit_ev_saveplace = (EditText) findViewById(R.id.edit_ev_saveplace);
        edit_ev_saveplace.setText(beanAddress.getLocation());
        edit_ev_savereplace = (EditText) findViewById(R.id.edit_ev_savereplace);
        edit_ev_savereplace.setText(beanAddress.getInfo());
        eidtaddress_tv_save = (Button) findViewById(R.id.editaddress_tv_save);
        editaddress_bt_del = (Button) findViewById(R.id.editaddress_bt_del);
    }

    private void setListener() {
        editaddress_bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 删除地址操作
                boolean result = MsgCenter.getInstanceMsgCenter().deleteAddress(MsgCenter.addressList.get(position).getAddrId());
                if (result) {
                    Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "数据异常或服务器正忙...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        eidtaddress_tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = MsgCenter.getInstanceMsgCenter().changeAddress(MsgCenter.addressList.get(position));
                if (result) {
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "数据异常或服务器正忙...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //检测是否
    private boolean checkInfo() {

        return false;
    }


}
