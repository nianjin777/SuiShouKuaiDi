package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edu.zucc.wmhxa.kuaishou.R;


public class AddressAddActivity extends AppCompatActivity {

    private View view;
    private EditText add_ev_savename;
    private EditText add_ev_savephone;
    private EditText add_ev_saveplace;
    private EditText add_ev_savereplace;
    private Button addaddress_tv_save;
    private Button addaddress_bt_del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);

        findViewById();


    }

    public void findViewById() {
        add_ev_savename = (EditText) findViewById(R.id.add_ev_savename);
        add_ev_savephone = (EditText) findViewById(R.id.add_ev_savephone);
        add_ev_saveplace = (EditText) findViewById(R.id.add_ev_saveplace);
        add_ev_savereplace = (EditText) findViewById(R.id.add_ev_savereplace);
        addaddress_tv_save = (Button) findViewById(R.id.addaddress_tv_save);
        addaddress_bt_del = (Button) findViewById(R.id.addaddress_bt_del);
    }

}
