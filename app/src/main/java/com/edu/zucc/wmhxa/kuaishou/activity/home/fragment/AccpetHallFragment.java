package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.mapapi.map.MapView;
import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.model.BeanThing;
import com.edu.zucc.wmhxa.kuaishou.util.ListViewUtil;
import com.edu.zucc.wmhxa.kuaishou.util.adapter.NearTaskAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/7/19.
 */

public class AccpetHallFragment extends Fragment {

    private View view;
    private static Fragment instanceFragment = null;
    private MapView mMapView;
    private ListView accept_lv;
    private List<Map<String, Object>> mapList;

    public static Fragment getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new AccpetHallFragment();
            return instanceFragment;
        } else {
            return instanceFragment;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hall_accept, container, false);

        initMap();
        findViewById();
        setListener();

        return view;
    }

    public void findViewById() {
        accept_lv = (ListView) view.findViewById(R.id.accept_lv);

        //造一个假数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taskname", "买烟");
        map.put("distance", "500米");
        map.put("text", "纯雅谢谢！");
        map.put("money", 2.0);
        List<BeanThing> thingList = new ArrayList<BeanThing>();
        thingList.add(new BeanThing("纯雅", "超市", 16.0, 2));
        map.put("things", thingList);
        mapList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            mapList.add(map);
        }

        accept_lv.setAdapter(new NearTaskAdapter(getContext(), mapList));
        ListViewUtil.setListViewHeightBasedOnChildren(accept_lv);
    }

    public void setListener() {
        //任务列表项目点击事件
        accept_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName("com.edu.zucc.wmhxa.kuaishou", "com.edu.zucc.wmhxa.kuaishou.activity.order.accept.AcceptOrderActivity");
//                SerializableMap sm = new SerializableMap();
//                sm.setMap(data);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("info", sm);
                intent.putExtra("info", (HashMap<String, Object>) mapList.get(position));
                startActivity(intent);
            }
        });
    }

    private void initMap() {
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
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.setVisibility(View.VISIBLE);
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        //在activity执行onPause时执mMapView. onPause ()，实现地图生命周期管理
        mMapView.setVisibility(View.INVISIBLE);
        mMapView.onPause();
        super.onPause();
    }


}
