package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/21.
 */

public class AddressChooseAcrtivity extends Activity implements View.OnClickListener {

    private ListView chooseaddre_lv;
    private Button title3_bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_choose);

        findViewById();
        setListener();

    }

    private void findViewById() {
        View chooseaddre_title = findViewById(R.id.chooseaddre_title);
        TextView title1_tv = (TextView) chooseaddre_title.findViewById(R.id.title3_tv);
        title3_bt = (Button) chooseaddre_title.findViewById(R.id.title3_bt);
        title3_bt.setText("管理");
        title1_tv.setText("选择收货地址");
        chooseaddre_lv = (ListView) findViewById(R.id.chooseaddre_lv);
    }

    private void setListener() {
        title3_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title3_bt:
                Intent intent = new Intent(AddressChooseAcrtivity.this, AddressManageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
