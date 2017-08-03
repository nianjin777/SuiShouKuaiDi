package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.edu.wmhxa.sskd.R;


public class AddressEditActivity extends Activity {

    private EditText edit_ev_savename;
    private EditText edit_ev_savephone;
    private EditText edit_ev_saveplace;
    private EditText edit_ev_savereplace;
    private Button eidtaddress_tv_save;
    private Button editaddress_bt_del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);

        findViewById();

    }

    public void findViewById() {
        edit_ev_savename = (EditText) findViewById(R.id.edit_ev_savename);
        edit_ev_savephone = (EditText) findViewById(R.id.edit_ev_savephone);
        edit_ev_saveplace = (EditText) findViewById(R.id.edit_ev_saveplace);
        edit_ev_savereplace = (EditText) findViewById(R.id.edit_ev_savereplace);
        eidtaddress_tv_save = (Button) findViewById(R.id.editaddress_tv_save);
        editaddress_bt_del = (Button) findViewById(R.id.editaddress_bt_del);
    }


}
