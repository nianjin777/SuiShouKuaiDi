package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.util.adapter.AddressAdapter;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import static com.edu.wmhxa.sskd.R.id.issue_lv;
import static com.edu.wmhxa.sskd.R.id.issue_tv_totle;
import static com.edu.wmhxa.sskd.control.MsgCenter.thingList;

/**
 * Created by Administrator on 2017/7/21.
 */

public class AddressManageActivity extends Activity implements OnClickListener {

    private ListView manageaddre_lv;
    private ImageView title3_back;
    private Button title3_bt;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    boolean result = (boolean) msg.obj;
                    if (result) {
                        Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                        manageaddre_lv.setAdapter(new AddressAdapter(getApplicationContext(), MsgCenter.addressList));
                    } else {
                        Toast.makeText(getApplicationContext(), "网络异常,请稍后再试", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);

        findViewById();
        setListener();
    }

    private void findViewById() {
        View manageaddre_title = findViewById(R.id.manageaddre_title);
        TextView title3_tv = (TextView) manageaddre_title.findViewById(R.id.title3_tv);
        title3_tv.setText("管理收货地址");
        title3_back = (ImageView) manageaddre_title.findViewById(R.id.title3_back);
        title3_bt = (Button) manageaddre_title.findViewById(R.id.title3_bt);
        title3_bt.setText("添加");

        manageaddre_lv = (ListView) findViewById(R.id.manageaddre_lv);
        manageaddre_lv.setAdapter(new AddressAdapter(getApplicationContext(), MsgCenter.addressList));
    }

    private void setListener() {
        title3_back.setOnClickListener(this);
        title3_bt.setOnClickListener(this);
        manageaddre_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AddressManageActivity.this, AddressEditActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
        manageaddre_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(AddressManageActivity.this).setTitle("删除地址")//设置对话框标题
                        .setMessage("是否要删除该地址")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                int choose = position;
                                if (position == 0) {
                                    choose = BeanAddress.indexDeault;
                                } else if (position < BeanAddress.indexDeault) {
                                    choose -= 1;
                                }
                                final BeanAddress beanAddress = MsgCenter.addressList.get(choose);
                                if (beanAddress.isAddrDefault()) {
                                    Toast.makeText(getApplicationContext(), "你不能删除默认地址", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        boolean result = MsgCenter.getInstanceMsgCenter().deleteAddress(beanAddress.getAddrId());
                                        Message message = new Message();
                                        message.what = 1;
                                        message.obj = result;
                                        handler.sendMessage(message);
                                    }
                                }).start();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        Log.i("alertdialog", " 取消删除取消删除地址");
                    }
                }).show();//在按键响应事件中显示此对话框
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title3_back:
                finish();
                break;
            case R.id.title3_bt:
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.setting.address.AddressAddActivity");
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        manageaddre_lv.setAdapter(new AddressAdapter(getApplicationContext(), MsgCenter.addressList));
    }
}
