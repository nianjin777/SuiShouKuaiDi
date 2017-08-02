package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.util.adapter.AddressAdapter;

/**
 * Created by Administrator on 2017/7/21.
 */

public class AddressManageActivity extends Activity implements OnClickListener {

    private ListView manageaddre_lv;
    private ImageView title3_back;
    private Button title3_bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);

        findViewById();
        setListener();
    }

    private void findViewById() {
        View manageaddre_title = findViewById(R.id.manageaddre_title);
        TextView title3_tv = (TextView) manageaddre_title.findViewById(R.id.title3_tv);
        title3_tv.setText("管理收货地址");
        title3_back = (ImageView) manageaddre_title.findViewById(R.id.title3_back);
        title3_bt = (Button) manageaddre_title.findViewById(R.id.title3_bt);
        title3_bt.setText("添加");

        manageaddre_lv = (ListView) findViewById(R.id.manageaddre_lv);
        manageaddre_lv.setAdapter(new AddressAdapter(getApplicationContext(), MsgCenter.addressList));
    }

    private void setListener() {
        title3_back.setOnClickListener(this);
        title3_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title3_back:
                finish();
                break;
            case R.id.title3_bt:
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.setting.address.AddressAddActivity");
                startActivity(intent);
                break;
        }
    }
}
