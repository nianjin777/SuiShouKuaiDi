package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.search.core.PoiInfo;
import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.order.issue.ChoosePositionActivity;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;

public class AddressEditActivity extends Activity {

    private EditText edit_ev_savename;
    private EditText edit_ev_savephone;
    private EditText edit_ev_savereplace;
    private Button edit_ev_saveplace;
    private Button editaddress_bt_del;
    private Button editaddress_bt_default;
    private Button title3_bt;
    private ImageView title3_back;

    private int position;
    private BeanAddress beanAddress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //修改的message
                    boolean result1 = (boolean) msg.obj;
                    if (result1) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "数据异常或服务器正忙...", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 2:
                    //改默认的message
                    boolean result2 = (boolean) msg.obj;
                    if (result2) {
                        Toast.makeText(getApplicationContext(), "设置成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "数据异常或服务器正忙...", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        if (position == -1) {
            finish();
        }
        if (position == 0) {
            position = BeanAddress.indexDeault;
        } else if (position <= BeanAddress.indexDeault) {
            position -= 1;
        }
        beanAddress = MsgCenter.addressList.get(position);

        findViewById();
        setListener();

    }

    private void findViewById() {
        //重现数据
        edit_ev_savename = (EditText) findViewById(R.id.edit_ev_savename);
        edit_ev_savename.setText(beanAddress.getName());
        edit_ev_savephone = (EditText) findViewById(R.id.edit_ev_savephone);
        edit_ev_savephone.setText(beanAddress.getPhone());
        edit_ev_saveplace = (Button) findViewById(R.id.edit_ev_saveplace);
        edit_ev_saveplace.setText(beanAddress.getLocation());
        edit_ev_savereplace = (EditText) findViewById(R.id.edit_ev_savereplace);
        edit_ev_savereplace.setText(beanAddress.getInfo());

        editaddress_bt_del = (Button) findViewById(R.id.editaddress_bt_del);
        editaddress_bt_default = (Button) findViewById(R.id.editaddress_bt_default);
        if (beanAddress.isAddrDefault()) {
            editaddress_bt_default.setBackgroundColor(Color.GRAY);
        } else {
            editaddress_bt_default.setBackgroundColor(Color.WHITE);
        }

        //标题栏
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
                final BeanAddress check = checkInfo();
                if (check == null) {
                    Toast.makeText(getApplicationContext(), "没有做任何修改", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean result = MsgCenter.getInstanceMsgCenter().changeAddress(check);
                            Message message = new Message();
                            message.what = 1;
                            message.obj = result;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
            }
        });
        title3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit_ev_saveplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressEditActivity.this, ChoosePositionActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        editaddress_bt_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beanAddress.isAddrDefault()) {
                    //已经是默认就什么也不做
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean result = MsgCenter.getInstanceMsgCenter().changeDefaultAddress(beanAddress.getAddrId());
                            Message message = new Message();
                            message.what = 2;
                            message.obj = result;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (data == null) {
                //取消回来的 啥也不做
                return;
            }
            final PoiInfo result = (PoiInfo) data.getParcelableExtra("result");
            if (result == null) {
                //没选择POI的
                final String location = data.getStringExtra("location");
                Log.i("cyan", location);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edit_ev_saveplace.setText(location);
                    }
                });
                return;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    edit_ev_saveplace.setText(result.address + " " + result.name);
                }
            });
        }
    }


}
