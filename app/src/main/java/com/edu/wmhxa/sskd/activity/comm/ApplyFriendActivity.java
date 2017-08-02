package com.edu.wmhxa.sskd.activity.comm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.util.adapter.FriApplyAdapter;

/**
 * Created by Administrator on 2017/7/29.
 */

public class ApplyFriendActivity extends Activity {

    private ImageView back;
    private EditText friapply_et_search;
    private Button friapply_bt_search;
    private ListView friapply_lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_apply);

        findViewById();
        setListener();
    }

    private void findViewById() {
        View friapply_title = findViewById(R.id.friapply_title);
        back = (ImageView) friapply_title.findViewById(R.id.title1_back);
        TextView title1_tv = (TextView) friapply_title.findViewById(R.id.title1_tv);
        title1_tv.setText("新的朋友");
        friapply_et_search = (EditText) findViewById(R.id.friapply_et_search);
        friapply_bt_search = (Button) findViewById(R.id.friapply_bt_search);
        friapply_lv = (ListView) findViewById(R.id.friapply_lv);
        friapply_lv.setAdapter(new FriApplyAdapter(getApplicationContext(), MsgCenter.applyFriendList));
    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        friapply_bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索功能
                Intent intent = new Intent(ApplyFriendActivity.this, AddFriendActivity.class);
                intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.comm.AddFriendActivity");
                String name = String.valueOf(friapply_et_search.getText());
                if (name == null || name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入需要查找的用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        boolean result = data.getBooleanExtra("result", false);
        if (result) {

        } else {
            Toast.makeText(getApplicationContext(), "无此用户", Toast.LENGTH_SHORT).show();
        }
    }
}
