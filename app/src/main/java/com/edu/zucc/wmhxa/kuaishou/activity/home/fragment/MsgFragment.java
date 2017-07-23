package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class MsgFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_msg, container, false);

        findViewById();

        return view;
    }

    private void findViewById() {
    }

}
