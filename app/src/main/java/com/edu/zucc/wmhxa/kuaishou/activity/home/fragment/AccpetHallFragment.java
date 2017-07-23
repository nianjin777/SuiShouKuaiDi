package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class AccpetHallFragment extends Fragment {

    private View view;
    private static Fragment instanceFragment = null;
    private MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hall_accept, container, false);


        findViewById();

        return view;
    }

    public static Fragment getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new AccpetHallFragment();
            return instanceFragment;
        } else {
            return instanceFragment;
        }
    }

    public void findViewById() {
        mMapView = (MapView) view.findViewById(R.id.mMapView);
    }

    public View getView() {
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
