package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.edu.zucc.wmhxa.kuaishou.R;

public class ThingsAddActivity extends Activity {

    private TextView add_setname;
    private TextView add_setplace;
    private TextView add_setmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_add);

        findViewById();

    }

    public void findViewById() {
        add_setname = (TextView) findViewById(R.id.add_setname);
        add_setmoney = (TextView) findViewById(R.id.add_setmoney);
        add_setplace = (TextView) findViewById(R.id.add_setplace);

    }
}
