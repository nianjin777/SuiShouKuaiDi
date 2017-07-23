package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.app.FragmentManager;
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

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.activity.home.HomeActivity;


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


    public void findViewById() {
        issue_tv_name = (TextView) view.findViewById(R.id.issue_tv_name);
        issue_tv_phone = (TextView) view.findViewById(R.id.issue_tv_phone);
        issue_tv_addr = (TextView) view.findViewById(R.id.issue_tv_addr);
        issue_rl_address = view.findViewById(R.id.issue_rl_address);
        issue_et_taskname = (EditText) view.findViewById(R.id.issue_et_taskname);
        issue_lv = (ListView) view.findViewById(R.id.issue_lv);
        issue_et_text = (EditText) view.findViewById(R.id.issue_et_text);
        issue_et_money = (EditText) view.findViewById(R.id.issue_et_money);
        issue_tv_totle = (TextView) view.findViewById(R.id.issue_tv_totle);
        issue_bt_addthing = (ImageButton) view.findViewById(R.id.issue_bt_addthing);
        issue_bt_cancel = (Button) view.findViewById(R.id.issue_bt_cancel);
        issue_bt_issue = (Button) view.findViewById(R.id.issue_bt_issue);

    }

    public void setListener() {
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
                intent.setClassName("com.edu.zucc.wmhxa.kuaishou", "com.edu.zucc.wmhxa.kuaishou.activity.home.AddressChooseAcrtivity");
                startActivity(intent);
                break;
            case R.id.issue_bt_addthing:
                intent.setClassName("com.edu.zucc.wmhxa.kuaishou", "com.edu.zucc.wmhxa.kuaishou.activity.home.ThingsAddActivity");
                startActivity(intent);
                break;
            case R.id.issue_bt_cancel:
                //Reset date
                HomeActivity activity = HomeActivity.activity;

                activity.fragmentsList.set(0, AccpetHallFragment.getInstanceFragment());
                activity.bottomAdapter.notifyDataSetChanged();


                activity.title2_tv_accept.setBackground(activity.getDrawable(R.color.white));
                activity.title2_tv_issue.setBackground(activity.getDrawable(R.color.head));

                break;
            case R.id.issue_bt_issue:
                //TODO 发布操作
                break;
        }
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

}