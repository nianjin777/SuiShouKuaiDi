package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/21.
 */

public class AddressManageActivity extends Activity implements OnClickListener {

    private ListView manageaddre_lv;
    private ImageView title1_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manage);

        findViewById();
        setListener();
    }

    private void findViewById() {
        View manageaddre_title = findViewById(R.id.manageaddre_title);
        TextView title1_tv = (TextView) manageaddre_title.findViewById(R.id.title1_tv);
        title1_back = (ImageView) manageaddre_title.findViewById(R.id.title1_back);
        title1_tv.setText("管理收货地址");
        manageaddre_lv = (ListView) findViewById(R.id.manageaddre_lv);
    }

    private void setListener() {
        title1_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title1_back:
                finish();
                break;
        }
    }
}
