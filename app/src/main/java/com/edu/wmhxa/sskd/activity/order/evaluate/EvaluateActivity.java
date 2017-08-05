package com.edu.wmhxa.sskd.activity.order.evaluate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.edu.wmhxa.sskd.model.BeanOrder;
import com.edu.wmhxa.sskd.util.adapter.ThingListAdapter;

import static com.edu.wmhxa.sskd.R.id.issue_lv;
import static com.edu.wmhxa.sskd.R.id.issue_tv_totle;
import static com.edu.wmhxa.sskd.control.MsgCenter.thingList;


public class EvaluateActivity extends Activity {

    private TextView eva_name;
    private TextView eva_state;
    private TextView eva_tv_task;
    private TextView eva_tv_dis;
    private TextView eva_tv_text;
    private TextView eva_tv_money;
    private EditText eva_et_text;
    private Button title3_bt;
    private ImageView title3_back;
    private BeanOrder beanOrder;
    private EditText eva_et_text2;
    private boolean canEdit = true;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    boolean result = (boolean) msg.obj;
                    if (result) {
                        Toast.makeText(getApplicationContext(), "评价成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "评价失败,请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if (position == -1) {
            return;
        }
        beanOrder = MsgCenter.finishOrderList.get(position);

        findViewById();
        setListener();
    }

    private void findViewById() {
        eva_name = (TextView) findViewById(R.id.eva_name);
        eva_state = (TextView) findViewById(R.id.eva_state);
        eva_tv_task = (TextView) findViewById(R.id.eva_tv_task);
        eva_tv_dis = (TextView) findViewById(R.id.eva_tv_dis);
        eva_tv_text = (TextView) findViewById(R.id.eva_tv_text);
        eva_tv_money = (TextView) findViewById(R.id.eva_tv_money);
        eva_et_text = (EditText) findViewById(R.id.eva_et_text);//对快递员评价
        eva_et_text2 = (EditText) findViewById(R.id.eva_et_text2);//对雇主评价

        View eval_title = findViewById(R.id.eval_title);
        title3_back = (ImageView) eval_title.findViewById(R.id.title3_back);
        TextView title3_tv = (TextView) eval_title.findViewById(R.id.title3_tv);
        title3_bt = (Button) eval_title.findViewById(R.id.title3_bt);
        title3_bt.setText("发布");
        title3_tv.setText("订单评价");

        //数据重现
        eva_name.setText(beanOrder.getEmpAccount().getName());
        eva_state.setText("已完成");
        eva_tv_task.setText(beanOrder.getOrderName());
        eva_tv_dis.setText(String.valueOf(beanOrder.getDistence()));
        eva_tv_text.setText(beanOrder.getOrderText());
        eva_tv_money.setText(String.valueOf(beanOrder.getBounty()));
        //编辑框的操作
        if (beanOrder.getEmpAccount().getUsername().equals(MsgCenter.beanUser.getUsername())) {
            //你是这个订单的快递员
            if (beanOrder.getEvalBoss() == null || beanOrder.getEvalBoss().isEmpty() || beanOrder.getEvalBoss().equals("null")) {
                //没进行评价过 可以进行评价
                //对编辑框设置可编辑
                eva_et_text2.setEnabled(true);
                eva_et_text.setEnabled(false);
                if (!beanOrder.getEvalEmp().equals("null") && beanOrder.getEvalEmp() != null) {
                    eva_et_text.setText(beanOrder.getEvalEmp());
                }
            } else {
                //已经评价过了 只能看 不能编辑
                eva_et_text2.setEnabled(false);
                eva_et_text.setEnabled(false);
                if (!beanOrder.getEvalBoss().equals("null") && beanOrder.getEvalBoss() != null) {
                    eva_et_text2.setText(beanOrder.getEvalBoss());
                }
                if (!beanOrder.getEvalEmp().equals("null") && beanOrder.getEvalEmp() != null) {
                    eva_et_text.setText(beanOrder.getEvalEmp());
                }
                canEdit = false;
            }
        } else {
            //你是这个订单的雇主
            if (beanOrder.getEvalEmp() == null || beanOrder.getEvalEmp().isEmpty() || beanOrder.getEvalEmp().equals("null")) {
                //没进行评价过 可以进行评价
                //对编辑框设置可编辑
                eva_et_text2.setEnabled(false);
                eva_et_text.setEnabled(true);
                eva_et_text.requestFocus();
                if (!beanOrder.getEvalBoss().equals("null") && beanOrder.getEvalBoss() != null) {
                    eva_et_text2.setText(beanOrder.getEvalBoss());
                }

            } else {
                //已经评价过了 只能看 不能编辑
                eva_et_text2.setEnabled(false);
                eva_et_text.setEnabled(false);
                eva_et_text.setText(beanOrder.getEvalEmp());
                if (!beanOrder.getEvalBoss().equals("null") && beanOrder.getEvalBoss() != null) {
                    eva_et_text2.setText(beanOrder.getEvalBoss());
                }
                if (!beanOrder.getEvalEmp().equals("null") && beanOrder.getEvalEmp() != null) {
                    eva_et_text.setText(beanOrder.getEvalEmp());
                }
                canEdit = false;
            }
        }

    }

    private void setListener() {
        title3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title3_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EvaluateActivity.this).setTitle("评价")//设置对话框标题
                        .setMessage("你确定要进行评价吗?评价后内容将不可修改")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String eval = getEval();
                                        if (eval == null) {
                                            Toast.makeText(getApplicationContext(), "你还没有进行任何评价", Toast.LENGTH_SHORT).show();
                                            return;
                                        } else {
                                            boolean result = MsgCenter.getInstanceMsgCenter().eval(beanOrder, eval);
                                            Message message = new Message();
                                            message.what = 1;
                                            message.obj = result;
                                            handler.sendMessage(message);
                                        }
                                    }
                                }).start();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        Log.i("alertdialog", "取消评价");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }

    private String getEval() {
        String sEval = "";
        if (beanOrder.getEmpAccount().getUsername().equals(MsgCenter.beanUser.getUsername())) {
            //你是这个订单的快递员
            sEval = eva_et_text2.getText().toString();
        } else {
            //你是这个订单的雇主
            sEval = eva_et_text.getText().toString();
        }
        if (sEval == null || sEval.isEmpty()) {
            //抓取的字符串为空
            return null;
        }
        return sEval;
    }

}
