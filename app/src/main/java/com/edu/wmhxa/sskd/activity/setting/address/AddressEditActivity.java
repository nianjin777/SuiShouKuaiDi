package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;

import static android.R.attr.name;
import static com.baidu.location.d.j.v;


public class AddressEditActivity extends Activity {

    private EditText edit_ev_savename;
    private EditText edit_ev_savephone;
    private EditText edit_ev_saveplace;
    private EditText edit_ev_savereplace;
    private Button editaddress_bt_del;
    private Button editaddress_bt_default;
    private Button title3_bt;
    private ImageView title3_back;

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
        editaddress_bt_del = (Button) findViewById(R.id.editaddress_bt_del);
        editaddress_bt_default = (Button) findViewById(R.id.editaddress_bt_default);
        View editaddress_title = findViewById(R.id.editaddress_title);
        title3_back = (ImageView) editaddress_title.findViewById(R.id.title3_back);
        TextView title3_tv = (TextView) editaddress_title.findViewById(R.id.title3_tv);
        title3_bt = (Button) editaddress_title.findViewById(R.id.title3_bt);
        title3_bt.setText("保存修改");
        title3_tv.setText("编辑地址");
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
        title3_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeanAddress check = checkInfo();
                if (check == null) {
                    Toast.makeText(getApplicationContext(), "没有做任何修改", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    boolean result = MsgCenter.getInstanceMsgCenter().changeAddress(MsgCenter.addressList.get(position));
                    if (result) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "数据异常或服务器正忙...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        title3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //检测是否更改
    private BeanAddress checkInfo() {
        boolean flag = false;
        BeanAddress change = beanAddress;

        String name = edit_ev_savename.getText().toString();
        String phone = edit_ev_savephone.getText().toString();
        String place = edit_ev_saveplace.getText().toString();
        String replace = edit_ev_savereplace.getText().toString();

        if (!change.getName().equals(name)) {
            flag = true;
            change.setName(name);
        }
        if (!change.getPhone().equals(phone)) {
            flag = true;
            change.setPhone(phone);
        }
        if (!change.getLocation().equals(place)) {
            flag = true;
            change.setLocation(place);
        }
        if (!change.getInfo().equals(replace)) {
            flag = true;
            change.setInfo(replace);
        }
        if (flag) {
            return change;
        } else {
            return null;
        }
    }


}
