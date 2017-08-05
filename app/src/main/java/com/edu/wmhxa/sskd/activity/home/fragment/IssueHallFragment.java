package com.edu.wmhxa.sskd.activity.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.home.HomeActivity;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanAddress;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.edu.wmhxa.sskd.activity.home.HomeActivity.activity;


/**
 * Created by Administrator on 2017/7/19.
 */

public class IssueHallFragment extends Fragment implements View.OnClickListener {

    private static IssueHallFragment instanceFragment = null;

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

    //消息机制操作
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //网络请求回调
                    int result = (int) msg.obj;
                    if (result == -1) {
                        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();
                        //跳转
                        HomeActivity activity = HomeActivity.activity;
                        HomeActivity.click = 0;
                        activity.fragmentsList.set(0, AcceptHallFragment.getInstanceFragment());
                        activity.bottomAdapter.notifyDataSetChanged();
                        activity.title2_tv_accept.setBackground(activity.getDrawable(R.color.white));
                        activity.title2_tv_issue.setBackground(activity.getDrawable(R.color.head));
                    }
                    break;

                default:
                    break;
            }
        }
    };

    public static IssueHallFragment getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new IssueHallFragment();
            return instanceFragment;
        } else {
            return instanceFragment;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hall_issue, container, false);

        findViewById();
        setListener();

        return view;
    }

    private void findViewById() {
        issue_tv_name = (TextView) view.findViewById(R.id.issue_tv_name);
        issue_tv_phone = (TextView) view.findViewById(R.id.issue_tv_phone);
        issue_tv_addr = (TextView) view.findViewById(R.id.issue_tv_addr);
        if (MsgCenter.addressList.size() == 0) {
            beanAddress = null;
        } else {
            beanAddress = MsgCenter.addressList.get(BeanAddress.indexDeault);
        }
        if (beanAddress == null) {
            issue_tv_name.setText("暂无地址");
        } else {
            issue_tv_name.setText(beanAddress.getName());
            issue_tv_phone.setText(beanAddress.getPhone());
            issue_tv_addr.setText(beanAddress.getLocation() + "\n" + beanAddress.getInfo());
        }

        issue_rl_address = view.findViewById(R.id.issue_rl_address);
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

    }

    private void setListener() {
        issue_rl_address.setOnClickListener(this);
        issue_bt_cancel.setOnClickListener(this);
        issue_bt_issue.setOnClickListener(this);
        issue_bt_addthing.setOnClickListener(this);

        issue_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity()).setTitle("删除物品")//设置对话框标题
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
                //重置主界面上的图标
                changeHomeTitle();
                break;

            case R.id.issue_bt_issue:
                //发布操作
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
        // 如果子界面是点的返回 那什么也不做就return
        if (data == null) {
            return;
        }
        // 添加物品界面的返回值
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

        }
        // 选择地址的返回操作
        else if (requestCode == 2) {
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

    private void changeHomeTitle() {
        HomeActivity activity = HomeActivity.activity;
        activity.title2_tv_accept.setBackground(activity.getDrawable(R.color.white));
        activity.title2_tv_issue.setBackground(activity.getDrawable(R.color.head));
        activity.fragmentsList.set(0, AcceptHallFragment.getInstanceFragment());
        activity.bottomAdapter.notifyDataSetChanged();
    }

    /**
     * 确认支付弹窗
     */
    private void issue(final BeanOrder beanOrder) {
        new AlertDialog.Builder(getContext()).setTitle("支付")//设置对话框标题
                .setMessage("佣金为" + String.valueOf(beanOrder.getBounty()) + "元" + "\n第三方支付暂未实现")//设置显示的内容
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
     * 抓取页面上的数据
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
            Toast.makeText(getContext(), "请输入一个任务名", Toast.LENGTH_SHORT).show();
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

    /**
     * 用MessageCenter与服务器交互
     */
    private void sendData(final BeanOrder beanOrder) {
        //TODO 发送一条订单数据到服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                int result = MsgCenter.getInstanceMsgCenter().addOrder(beanOrder);
                Message message = new Message();
                message.what = 1;
                message.obj = result;
                handler.sendMessage(message);
            }
        }).start();
    }

}
