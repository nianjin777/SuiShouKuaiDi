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

public class AccpetHallFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hall_accept, container, false);
        return view;
    }

    public void findViewById() {
    }

    public View getView() {
        return view;
    }

}
