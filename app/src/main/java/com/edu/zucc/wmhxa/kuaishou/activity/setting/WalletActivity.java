package com.edu.zucc.wmhxa.kuaishou.activity.setting;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/21.
 */

public class WalletActivity extends Activity {

    private TextView wallet_tv_money;
    private ListView wallet_lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        findViewById();

    }

    public void findViewById() {
        wallet_tv_money = (TextView) findViewById(R.id.wallet_tv_money);
        wallet_lv = (ListView) findViewById(R.id.wallet_lv);
    }

}
