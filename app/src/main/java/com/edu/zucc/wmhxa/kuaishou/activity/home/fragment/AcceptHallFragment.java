package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.activity.home.HomeActivity;
import com.edu.zucc.wmhxa.kuaishou.control.MsgCenter;
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

public class AcceptHallFragment extends Fragment {

    private String TAG = "AccpetHallFragment";

    private TextureMapView mMapView;
    private BaiduMap baiduMap;
    private boolean firstLocation;
    private LocationClient mLocationClient;
    public static LatLng xy = null;

    private View view;
    private static Fragment instanceFragment = null;
    private ListView accept_lv;
    private List<Map<String, Object>> orderList;
    private ScrollView accept_sv;
    private Button accept_location;

    public static Fragment getInstanceFragment() {
        if (instanceFragment == null) {
            instanceFragment = new AcceptHallFragment();
            return instanceFragment;
        } else {
            return instanceFragment;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            accept_lv.setAdapter(new NearTaskAdapter(getContext(), orderList));
            ListViewUtil.setListViewHeightBasedOnChildren(accept_lv);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hall_accept, container, false);
        //初始化数据
        initData();
        findViewById();

        //权限检查与申请
        while (!(ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                !(ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
        }
        initMap();
        setListener();

        return view;
    }

    private void initData() {
        orderList = MsgCenter.nearTaskList;
    }

    public void findViewById() {
        accept_sv = (ScrollView) view.findViewById(R.id.accept_sv);
        accept_lv = (ListView) view.findViewById(R.id.accept_lv);
        accept_location = (Button) view.findViewById(R.id.accept_location);
    }

    public void setListener() {
        accept_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMap();
            }
        });
        //任务列表项目点击事件
        accept_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName("com.edu.zucc.wmhxa.kuaishou", "com.edu.zucc.wmhxa.kuaishou.activity.order.accept.AcceptOrderActivity");
                intent.putExtra("info", (HashMap<String, Object>) orderList.get(position));
                startActivity(intent);
            }
        });
        View v = mMapView.getChildAt(0);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    HomeActivity.menu.requestDisallowInterceptTouchEvent(false);
                    HomeActivity.mViewPager.requestDisallowInterceptTouchEvent(false);
                    accept_sv.requestDisallowInterceptTouchEvent(false);
                } else {
                    HomeActivity.menu.requestDisallowInterceptTouchEvent(true);
                    HomeActivity.mViewPager.requestDisallowInterceptTouchEvent(true);
                    accept_sv.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

    //初始化地图
    private void initMap() {
        mMapView = (TextureMapView) view.findViewById(R.id.mMapView);
        baiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18f);
        baiduMap.setMapStatus(msu);

        //声明LocationClient类
        mLocationClient = new LocationClient(getContext());
        initLocation();
        firstLocation = true;

        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                // map view 销毁后不在处理新接收的位置
                if (location == null || mMapView == null)
                    return;
                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                // 设置定位数据
                baiduMap.setMyLocationData(locData);

                xy = new LatLng(location.getLatitude(), location.getLongitude());
                // 第一次定位时，将地图位置移动到当前位置 并获取当前位置下的任务列表
                if (firstLocation) {
                    firstLocation = false;
                    MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(xy);
                    baiduMap.animateMapStatus(status);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }

            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        });
        mLocationClient.start();
    }

    //初始化Location数据
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public View getView() {
        return view;
    }

    @Override
    public void onStart() {
        // 如果要显示位置图标,必须先开启图层定位
        baiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        // 关闭图层定位
        baiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()
        mMapView.onDestroy();
        mMapView = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()
        mMapView.onPause();
    }
}