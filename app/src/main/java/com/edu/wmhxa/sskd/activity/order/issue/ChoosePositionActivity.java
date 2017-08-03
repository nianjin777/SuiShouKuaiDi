package com.edu.wmhxa.sskd.activity.order.issue;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.edu.wmhxa.sskd.R;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * Created by Administrator on 2017/7/28.
 */

public class ChoosePositionActivity extends Activity implements
        OnGetPoiSearchResultListener {
    private static final String TAG = "cyan";
    private boolean inFlash = false;

    private TextView cancel;
    private Button ok;
    private Button location;
    private ListView lv_near;
    private MapView mMapView;
    private BaiduMap baiduMap;

    private LocationClient mLocationClient;
    private boolean firstLocation;
    private LatLng xy;
    private String nowLocation;
    private List<PoiInfo> dataList = new ArrayList<PoiInfo>();
    private ListAdapter adapter = new ListAdapter(0);
    private PoiSearch mPoiSearch;
    private String keyWord;
    private Marker marker;
    private Button bt;
    private EditText et;
    private String sendAddress;
    private TextView dqwz;
    PoiResult poiResult = null;
    PoiInfo result = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.i(TAG, "---------------------");
                    if (inFlash) {
                    } else {
                        adapter.notifyDataSetChanged();
                        if (poiResult == null) {
                            result = dataList.get(0);
                        }
                    }
                    break;
                case 1:
                    //刷新列表的
                    Log.i(TAG, "---------------------正在刷新列表信息");
                    adapter.notifyDataSetChanged();
                    if (poiResult == null) {
                        result = dataList.get(0);
                    }
                    inFlash = false;
                    break;
                default:
                    break;
            }
        }
    };
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_chooseposition);
        //权限检查与申请
        while (!(ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                !(ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
        }

        findViewById();
        initMap();
        setListener();

    }

    private void findViewById() {
        cancel = (TextView) findViewById(R.id.my_tv_cancel);
        ok = (Button) findViewById(R.id.mp_bt_ok);
        location = (Button) findViewById(R.id.location);
        lv_near = (ListView) findViewById(R.id.lv_near);
        mMapView = (MapView) findViewById(R.id.mMapView);
        bt = (Button) findViewById(R.id.bt);
        et = (EditText) findViewById(R.id.et);
        dqwz = (TextView) findViewById(R.id.dqwz);//当前位置
    }

    private void setListener() {
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMap();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(10);
                finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result", result);
                intent.putExtra("location", address);
                setResult(10, intent);
                finish();
            }
        });
        lv_near.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == dataList.size()) {
                    if (poiResult == null || poiResult.getAllPoi().size() == dataList.size()) {
                        Toast.makeText(getContext(), "没有更多啦", Toast.LENGTH_SHORT).show();
                    } else {
                        inFlash = true;
                        getPoi();
                    }
                    return;
                }
                adapter.setCheckposition(position);
                result = dataList.get(position);
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                keyWord = String.valueOf(et.getText());
                if (keyWord == null && keyWord.equals("")) {
                    Toast.makeText(getContext(), "请输入关键字", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    getPoi();
                    lv_near.setAdapter(adapter);
                }
            }
        });
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                marker.setPosition(mapStatus.target);
                final GeoCoder geoCoder = GeoCoder.newInstance();
                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(mapStatus.target));
                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        address = reverseGeoCodeResult.getAddress();
                        if (address == null && address.isEmpty()) {
                            Toast.makeText(getContext(), "获取地址失败", Toast.LENGTH_SHORT).show();
                        } else {
                            dqwz.setText(address);
                            Log.i(TAG, "重定位地址：" + address);
                            if (dataList.size() != 0) {
                                keyWord = String.valueOf(et.getText());
                                dataList.clear();
                                inFlash = true;
                                getPoi();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        });
    }

    //初始化地图
    private void initMap() {
        baiduMap = mMapView.getMap();
        baiduMap.clear();
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
                nowLocation = location.getAddrStr();
                Log.i(TAG, "当前定位位置为：" + nowLocation);
                xy = new LatLng(location.getLatitude(), location.getLongitude());

                // 第一次定位时，将地图位置移动到当前位置 并获取当前位置下的任务列表
                if (firstLocation) {
                    OverlayOptions ooA = new MarkerOptions()
                            .position(xy)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_marka))
                            .visible(false)
                            .draggable(true);
                    marker = (Marker) baiduMap.addOverlay(ooA);
                    firstLocation = false;
                    MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(xy);
                    baiduMap.animateMapStatus(status);
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
        int span = 5 * 1000;
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

    private void getPoi() {
        //poi 搜索周边
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Looper.prepare();
                searchNeayBy();
                Looper.loop();
            }
        }).start();
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        // 获取POI检索结果
        // 没有找到检索结果
        this.poiResult = poiResult;
        if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Log.i(TAG, "keyword:" + keyWord);
            if (keyWord == null || keyWord.isEmpty()) {
            } else {
                Toast.makeText(ChoosePositionActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
            }
            return;
        }
        // 检索结果正常返回
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
            if (poiResult != null) {
                if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                    dataList.addAll(poiResult.getAllPoi());
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    // POI初始化搜索模块，注册搜索事件监听
    private void searchNeayBy() {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        PoiNearbySearchOption poiNearbySearchOption = new PoiNearbySearchOption();

        //关键字
        poiNearbySearchOption.keyword(keyWord);
        poiNearbySearchOption.location(xy);
        poiNearbySearchOption.radius(2000);  // 检索半径，单位是米
        poiNearbySearchOption.pageCapacity(20);  // 默认每页10条
        mPoiSearch.searchNearby(poiNearbySearchOption);  // 发起附近检索请求
    }

    private class ListAdapter extends BaseAdapter {

        private int checkPosition;

        public ListAdapter(int checkPosition) {
            this.checkPosition = checkPosition;
        }

        public void setCheckposition(int checkPosition) {
            this.checkPosition = checkPosition;
        }

        @Override
        public int getCount() {
//            return dataList.size() + 1;
            if (dataList.size() == 0) {
                return 0;
            } else {
                return dataList.size() + 1;
            }
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getContext(), R.layout.item_list, null);
            if (position == dataList.size()) {
                view = View.inflate(getContext(), R.layout.item_more, null);
            } else {
                TextView text_name = (TextView) view.findViewById(R.id.text_name);
                TextView text_address = (TextView) view.findViewById(R.id.text_address);
                ImageView image = (ImageView) view.findViewById(R.id.image);
                text_name.setText(dataList.get(position).name);
                text_address.setText(dataList.get(position).address);
                if (checkPosition == position) {
                    image.setVisibility(View.VISIBLE);
                } else {
                    image.setVisibility(View.GONE);
                }
            }
            return view;
        }
    }

    @Override
    protected void onStart() {
        // 如果要显示位置图标,必须先开启图层定位
        baiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        // 关闭图层定位
        baiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()
        mMapView.onDestroy();
        mMapView = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()
        mMapView.onPause();
    }

}
