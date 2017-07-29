package com.edu.zucc.wmhxa.kuaishou.activity.order.issue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.baidu.mapapi.search.core.PoiInfo;
import com.edu.zucc.wmhxa.kuaishou.model.BeanThing;

import com.edu.zucc.wmhxa.kuaishou.R;

public class ThingsAddActivity extends Activity implements View.OnClickListener {

    private EditText add_et_setname;
    private Button add_bt_setplace;
    private EditText add_et_setmoney;
    private Button add_bt_cancel;
    private Button add_bt_add;
    private ImageView title1_back;
    private EditText add_et_setnumber;

    private BeanThing beanThing = null;
    private double longitude = 0;
    private double latitude = 0;
    private String address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_add);

        findViewById();
        setListener();

    }

    private void findViewById() {
        View add_title1 = findViewById(R.id.add_title1);
        TextView title1_tv = (TextView) add_title1.findViewById(R.id.title1_tv);
        title1_back = (ImageView) add_title1.findViewById(R.id.title1_back);
        title1_tv.setText("添加物品");

        add_et_setnumber = (EditText) findViewById(R.id.add_et_setnumber);
        add_et_setname = (EditText) findViewById(R.id.add_et_setname);
        add_bt_setplace = (Button) findViewById(R.id.add_bt_setplace);
        add_et_setmoney = (EditText) findViewById(R.id.add_et_setmoney);
        add_bt_cancel = (Button) findViewById(R.id.add_bt_cancel);
        add_bt_add = (Button) findViewById(R.id.add_bt_add);
    }

    private void setListener() {
        title1_back.setOnClickListener(this);
        add_bt_cancel.setOnClickListener(this);
        add_bt_add.setOnClickListener(this);
        add_bt_setplace.setOnClickListener(this);
    }

    private BeanThing getData() {
        BeanThing thing = new BeanThing();
        thing.setName(String.valueOf(add_et_setname.getText()));
        thing.setLongitude(longitude);
        thing.setLatitude(latitude);
        thing.setAddress(address);
        thing.setMoney(Double.valueOf(String.valueOf(add_et_setmoney.getText())));
        thing.setNumber(Integer.valueOf(String.valueOf(add_et_setnumber.getText())));

        return thing;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title1_back:
                finish();
                break;
            case R.id.add_bt_cancel:
                finish();
                break;
            case R.id.add_bt_add:
                //TODO 执行添加操作
                beanThing = getData();

                Bundle bundle = new Bundle();
                bundle.putSerializable("thing", beanThing);
                intent.putExtras(bundle);
                this.setResult(10, intent);
                finish();

                break;
            case R.id.add_bt_setplace:
                //TODO 点击跳转到一个手动定位的地图页面
                intent.setClassName("com.edu.zucc.wmhxa.kuaishou", "com.edu.zucc.wmhxa.kuaishou.activity.order.issue.ChoosePositionActivity");
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            //取消回来的 啥也不做
            return;
        }
        final PoiInfo result = (PoiInfo) data.getParcelableExtra("result");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                add_bt_setplace.setText(result.address + " " + result.name);
            }
        });
    }
}