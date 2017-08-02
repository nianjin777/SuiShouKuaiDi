package com.edu.wmhxa.sskd.activity.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.activity.home.HomeActivity;
import com.edu.wmhxa.sskd.model.BeanThing;
import com.edu.wmhxa.sskd.util.ListViewUtil;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import java.util.ArrayList;
import java.util.List;


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

        //lv也要加监听
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.issue_rl_address:
                //选择地址
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.setting.address.AddressChooseAcrtivity");
                startActivity(intent);
                break;
            case R.id.issue_bt_addthing:
                //添加物品
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.order.issue.ThingsAddActivity");
                startActivityForResult(intent, 1);
                break;
            case R.id.issue_bt_cancel:
                //点击取消
                //重置数据
                HomeActivity activity = HomeActivity.activity;
                activity.title2_tv_accept.setBackground(activity.getDrawable(R.color.white));
                activity.title2_tv_issue.setBackground(activity.getDrawable(R.color.head));
                activity.fragmentsList.set(0, AcceptHallFragment.getInstanceFragment());
                activity.bottomAdapter.notifyDataSetChanged();

                break;
            case R.id.issue_bt_issue:
                //TODO 发布操作
                getData();
                issue();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        BeanThing thing = (BeanThing) data.getExtras().get("thing");
        thingList.add(thing);
        issue_lv.setAdapter(new ThingListAdapter(getContext(), thingList));
        ListViewUtil.setListViewHeightBasedOnChildren(issue_lv);
    }

    //发布信息弹窗
    private void issue() {
        new AlertDialog.Builder(getContext()).setTitle("支付")//设置对话框标题
                .setMessage("是否进行支付(第三方接口未实现，点击是进行支付)")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        Toast.makeText(getContext(), "发布成功", Toast.LENGTH_SHORT).show();
                        //发送数据
                        sendData();

                        //跳转
                        HomeActivity activity = HomeActivity.activity;
                        HomeActivity.click = 0;
                        activity.fragmentsList.set(0, AcceptHallFragment.getInstanceFragment());
                        activity.bottomAdapter.notifyDataSetChanged();
                        activity.title2_tv_accept.setBackground(activity.getDrawable(R.color.white));
                        activity.title2_tv_issue.setBackground(activity.getDrawable(R.color.head));
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

    private void getData() {
        String bossName = (String) issue_tv_name.getText();
        String phone = (String) issue_tv_phone.getText();
        String Address = (String) issue_tv_addr.getText();
        String orderName = String.valueOf(issue_et_taskname.getText());
        // thingList 可以直接拿过去发
        String orderText = String.valueOf(issue_et_text.getText());
    }

    private void sendData() {
        //TODO 发送一条订单数据到服务器
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

}
