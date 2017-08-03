package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
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

public class AddressChooseAcrtivity extends Activity implements View.OnClickListener {

    private ListView chooseaddre_lv;
    private Button title3_bt;
    private ImageView title3_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_choose);

        findViewById();
        setListener();

    }

    private void findViewById() {
        View chooseaddre_title = findViewById(R.id.chooseaddre_title);
        title3_back = (ImageView) chooseaddre_title.findViewById(R.id.title3_back);
        TextView title3_tv = (TextView) chooseaddre_title.findViewById(R.id.title3_tv);
        title3_bt = (Button) chooseaddre_title.findViewById(R.id.title3_bt);
        title3_bt.setText("管理");
        title3_tv.setText("选择收货地址");
        chooseaddre_lv = (ListView) findViewById(R.id.chooseaddre_lv);
        chooseaddre_lv.setAdapter(new AddressAdapter(getApplicationContext(), MsgCenter.addressList));
    }

    private void setListener() {
        title3_bt.setOnClickListener(this);
        title3_back.setOnClickListener(this);
        chooseaddre_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("position", position);
                setResult(10, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title3_bt:
                Intent intent = new Intent(AddressChooseAcrtivity.this, AddressManageActivity.class);
                startActivity(intent);
                break;
            case R.id.title3_back:
                finish();
                break;
        }
    }
}
