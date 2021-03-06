package com.edu.wmhxa.sskd.activity.order;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.home.HomeActivity;
import com.edu.wmhxa.sskd.activity.home.fragment.AcceptHallFragment;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;


/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderResultActivity extends Activity implements View.OnClickListener {

    private View orderresult_title;
    private View view;
    private TextView issue_tv_name;
    private TextView issue_tv_phone;
    private TextView issue_tv_addr;
    private View issue_rl_address;
    private EditText issue_et_taskname;
    private ListView issue_lv;
    private EditText issue_et_text;
    private EditText issue_et_money;
    private TextView issue_tv_totle;
    private ImageButton issue_bt_addthing;
    private Button issue_bt_cancel;
    private Button issue_bt_issue;

    private List<BeanThing> thingList = new ArrayList<BeanThing>();
    private BeanAddress beanAddress;
    private double totle = 0;
    private int position;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //网络请求回调
                    int result = (int) msg.obj;
                    if (result == -1) {
                        Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    } else {
                        //跳转
                        Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };
    private ImageView back;
    private String lv;
    private BeanOrder order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderresult);
        Intent intent = getIntent();
        position = intent.getIntExtra("position", BeanAddress.indexDeault);
        lv = intent.getStringExtra("lv");
        if (lv.equals("wait")) {
            order = MsgCenter.waitOrderList.get(position);
        } else if (lv.equals("ing")) {
            order = MsgCenter.ingOrderList.get(position);
        }

        findViewById();
        setListener();

    }

    private void findViewById() {
        //标题
        orderresult_title = findViewById(R.id.orderresult_title);
        TextView title1_tv = (TextView) orderresult_title.findViewById(R.id.title1_tv);
        back = (ImageView) orderresult_title.findViewById(R.id.title1_back);
        title1_tv.setText("订单详情");

        //拿到对象 设置数据
        view = findViewById(R.id.orderresult_fragment);
        beanAddress = order.getAddress();
        if (beanAddress == null) {
            issue_tv_name.setText("暂无地址");
        } else {
            issue_tv_name = (TextView) view.findViewById(R.id.issue_tv_name);
            issue_tv_phone = (TextView) view.findViewById(R.id.issue_tv_phone);
            issue_tv_addr = (TextView) view.findViewById(R.id.issue_tv_addr);
            issue_tv_name.setText(beanAddress.getName());
            issue_tv_phone.setText(beanAddress.getPhone());
            issue_tv_addr.setText(beanAddress.getLocation() + "\n" + beanAddress.getInfo());
        }

        issue_et_taskname = (EditText) view.findViewById(R.id.issue_et_taskname);
        //设置lv
        issue_lv = (ListView) view.findViewById(R.id.issue_lv);
        issue_lv.setAdapter(new ThingListAdapter(getContext(), thingList));
        issue_et_text = (EditText) view.findViewById(R.id.issue_et_text);
        issue_et_money = (EditText) view.findViewById(R.id.issue_et_money);
        issue_tv_totle = (TextView) view.findViewById(R.id.issue_tv_totle);
        issue_bt_addthing = (ImageButton) view.findViewById(R.id.issue_bt_addthing);
        issue_bt_cancel = (Button) view.findViewById(R.id.issue_bt_cancel);
        issue_bt_issue = (Button) view.findViewById(R.id.issue_bt_issue);

        issue_et_taskname.setText(order.getOrderName());
        issue_et_text.setText(order.getOrderText());
        issue_et_money.setText(String.valueOf(order.getBounty()));
        List<BeanThing> thingList = order.getThingList();
        for (int i = 0; i < thingList.size(); i++) {
            totle += thingList.get(i).getMoney();
        }
        issue_tv_totle.setText(String.valueOf(totle));
        issue_bt_issue.setText("修改订单");

    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        issue_rl_address.setOnClickListener(this);
        issue_bt_cancel.setOnClickListener(this);
        issue_bt_issue.setOnClickListener(this);
        issue_bt_addthing.setOnClickListener(this);
        issue_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(OrderResultActivity.this).setTitle("删除物品")//设置对话框标题
                        .setMessage("是否要删除该物品")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                totle -= thingList.get(position).getMoney();
                                issue_tv_totle.setText(String.valueOf(totle));
                                thingList.remove(position);
                                issue_lv.setAdapter(new ThingListAdapter(getContext(), thingList));
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        Log.i("alertdialog", " 取消删除物品");
                    }
                }).show();//在按键响应事件中显示此对话框
                return true;
            }
        });
        issue_et_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.toString().isEmpty()) {
                    issue_tv_totle.setText(String.valueOf(totle));
                } else {
                    String ss = s.toString();
                    Double bounty = Double.valueOf(ss);
                    issue_tv_totle.setText(String.valueOf(totle + bounty));
                }
            }
        });
        //lv也要加监听
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.issue_rl_address:
                //选择地址
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.setting.address.AddressChooseAcrtivity");
                startActivityForResult(intent, 2);
                break;
            case R.id.issue_bt_addthing:
                //添加物品
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.order.issue.ThingsAddActivity");
                startActivityForResult(intent, 1);
                break;
            case R.id.issue_bt_cancel:
                //点击取消
                //重置数据
                finish();

                break;
            case R.id.issue_bt_issue:
                //TODO 发布操作
                BeanOrder data = getData();
                if (data == null) {
                    return;
                } else {
                    issue(data);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            BeanThing thing = (BeanThing) data.getExtras().get("thing");
            thingList.add(thing);
            totle += thing.getMoney();
            issue_lv.setAdapter(new ThingListAdapter(getContext(), thingList));
            ListViewUtil.setListViewHeightBasedOnChildren(issue_lv);
            String sBounty = issue_et_money.getText().toString();
            if (sBounty != null && !sBounty.isEmpty()) {
                Double bounty = Double.valueOf(sBounty);
                issue_tv_totle.setText(String.valueOf(totle + bounty));
            } else {
                issue_tv_totle.setText(String.valueOf(totle));
            }

        } else if (requestCode == 2) {
            int position = data.getIntExtra("position", BeanAddress.indexDeault);
            if (position <= BeanAddress.indexDeault) {
                if (position == 0) {
                    position = BeanAddress.indexDeault;
                } else {
                    position -= 1;
                }
            }

            beanAddress = MsgCenter.addressList.get(position);
            issue_tv_name.setText(beanAddress.getName());
            issue_tv_phone.setText(beanAddress.getPhone());
            issue_tv_addr.setText(beanAddress.getLocation() + "\n" + beanAddress.getInfo());
        }
    }

    //发布信息弹窗
    private void issue(final BeanOrder beanOrder) {
        new AlertDialog.Builder(getContext()).setTitle("支付")//设置对话框标题
                .setMessage("佣金为" + String.valueOf(beanOrder.getBounty()) + "元")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        //发送数据
                        sendData(beanOrder);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                Toast.makeText(getContext(), "发布失败", Toast.LENGTH_SHORT).show();
            }
        }).show();//在按键响应事件中显示此对话框
    }

    /**
     * 用MessageCenter与服务器交互
     */

    private BeanOrder getData() {
        BeanOrder beanOrder = new BeanOrder();
        if (beanAddress == null) {
            Toast.makeText(getContext(), "需要添加一个地址", Toast.LENGTH_SHORT).show();
            return null;
        }
        beanOrder.setAddress(beanAddress);
        String name = issue_et_taskname.getText().toString();
        if (name == null || name.isEmpty()) {
            Toast.makeText(getContext(), "请输入一个用户名", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            beanOrder.setOrderName(name);
        }
        if (thingList.size() == 0) {
            Toast.makeText(getContext(), "请添加至少一个物品", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            beanOrder.setThingList(thingList);
        }
        beanOrder.setMoney(totle);
        beanOrder.setOrderText(issue_et_text.getText().toString());

        String sBounty = issue_et_money.getText().toString();
        if (sBounty == null || sBounty.isEmpty()) {
            Toast.makeText(getContext(), "请输入酬金", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            Double bounty = Double.valueOf(sBounty);
            beanOrder.setBounty(bounty);
        }
        return beanOrder;
    }

    private void sendData(final BeanOrder beanOrder) {
        //TODO 发送一条订单数据到服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                int result = MsgCenter.getInstanceMsgCenter().changeOrder(beanOrder);
                Message message = new Message();
                message.what = 1;
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
    }
}
