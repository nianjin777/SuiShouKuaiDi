package com.edu.wmhxa.sskd.activity.comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.edu.wmhxa.sskd.R;

public class MesFriActivity extends AppCompatActivity {

    private ListView mesfri_title;
    private ListView mesfri_lv_meswindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_fri);
        findViewById();
    }

    private void findViewById() {
        View mesfri_title = findViewById(R.id.mesfri_title);
        TextView title1_tv = (TextView) mesfri_title.findViewById(R.id.title1_tv);
        title1_tv.setText("好友名称");
        mesfri_lv_meswindow = (ListView) findViewById(R.id.mesfri_lv_meswindow);
    }
}
