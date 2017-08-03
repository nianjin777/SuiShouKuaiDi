package com.edu.wmhxa.sskd.activity.setting.address;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;


public class AddressEditActivity extends Activity {

    private EditText edit_ev_savename;
    private EditText edit_ev_savephone;
    private EditText edit_ev_saveplace;
    private EditText edit_ev_savereplace;
    private Button editaddress_bt_del;
    private Button title3_bt;
    private ImageView title3_back;

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
        editaddress_bt_del = (Button) findViewById(R.id.editaddress_bt_del);
        View editaddress_title = findViewById(R.id.editaddress_title);
        title3_back = (ImageView) editaddress_title.findViewById(R.id.title3_back);
        TextView title3_tv = (TextView) editaddress_title.findViewById(R.id.title3_tv);
        title3_bt = (Button) editaddress_title.findViewById(R.id.title3_bt);
        title3_bt.setText("保存");
        title3_tv.setText("编辑收货地址");
    }


}
