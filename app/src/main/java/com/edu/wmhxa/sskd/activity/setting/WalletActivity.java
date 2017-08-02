package com.edu.wmhxa.sskd.activity.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;


/**
 * Created by Administrator on 2017/7/21.
 */

public class WalletActivity extends Activity implements View.OnClickListener {

    private TextView wallet_tv_money;
    private ListView wallet_lv;
    private ImageView title1_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        findViewById();
        setListener();
    }

    private void findViewById() {
        View wallet_title = findViewById(R.id.wallet_title);
        TextView title1_tv = (TextView) wallet_title.findViewById(R.id.title1_tv);
        title1_back = (ImageView) wallet_title.findViewById(R.id.title1_back);
        title1_tv.setText("钱包");
        wallet_tv_money = (TextView) findViewById(R.id.wallet_tv_money);
        wallet_lv = (ListView) findViewById(R.id.wallet_lv);

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
