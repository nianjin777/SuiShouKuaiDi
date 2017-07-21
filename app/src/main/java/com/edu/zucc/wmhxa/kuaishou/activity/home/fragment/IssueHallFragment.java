package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class IssueHallFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView issue_tv_name;
    private TextView issue_tv_phone;
    private TextView issue_tv_addr;
    private View issue_rl_address;

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
    }

    public void setListener() {
        issue_rl_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.issue_rl_address:
                Intent intent = new Intent();
                intent.setClassName("com.edu.zucc.wmhxa.kuaishou", "com.edu.zucc.wmhxa.kuaishou.activity.home.AddressChooseAcrtivity");
                startActivity(intent);
                break;
        }
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

}
