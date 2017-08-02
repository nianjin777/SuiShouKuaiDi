package com.edu.wmhxa.kuaishou.activity.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.kuaishou.R;

public class HistoryEvalActivity extends AppCompatActivity {

    private View view;
    private ListView history_title;
    private ImageView history_head;
    private TextView history_name;
    private TextView history_tv_task;
    private TextView history_tv_dis;
    private TextView history_tv_money;
    private TextView history_et_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_eval);
        findViewById();
    }
    private void findViewById(){
        View history_title = findViewById(R.id.history_title);
        TextView title1_tv = (TextView) history_title.findViewById(R.id.title1_tv);
        title1_tv.setText("评价");
        history_head=(ImageView) view.findViewById(R.id.history_head);
        history_name=(TextView)view.findViewById(R.id.history_name);
        history_tv_task=(TextView)view.findViewById(R.id.history_tv_task);
        history_tv_dis=(TextView)view.findViewById(R.id.history_tv_dis);
        history_tv_money=(TextView)view.findViewById(R.id.history_tv_money);
        history_et_text=(TextView)view.findViewById(R.id.history_et_text);
    }
}
