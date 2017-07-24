package com.edu.zucc.wmhxa.kuaishou.activity.comm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edu.zucc.wmhxa.kuaishou.R;

public class SendFriDynActivity extends Activity {
    private View view;
    private Button sendfridyn_bt_exit;
    private Button sendfridyn_bt_send;
    private EditText sendfridyn_ev_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_fri_dyn);
        findViewById();
    }

    public void findViewById() {
        sendfridyn_bt_exit = (Button) view.findViewById(R.id.sendfridyn_bt_exit);
        sendfridyn_bt_send = (Button) view.findViewById(R.id.sendfridyn_bt_send);
        sendfridyn_ev_text = (EditText) view.findViewById(R.id.sendfridyn_ev_text);
    }

}
