package com.edu.wmhxa.sskd.activity.home.menu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class LeftMenu {

    private final static String TAG = "LeftMenu";

    private SlidingMenu menu;
    private Activity activity;
    private List<String> itemList;

    public LeftMenu(Activity activity) {
        this.activity = activity;
    }

    public SlidingMenu getLeftMenu() {
        menu = new SlidingMenu(activity);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.activity_leftmenu);
        itemList = new ArrayList<String>();
        itemList.add("地址");
        itemList.add("订单");
        itemList.add("设置");
        ListView menu_lv = (ListView) menu.findViewById(R.id.leftmenu_lv);
        TextView name = (TextView) menu.findViewById(R.id.leftmenu_name);
        if (MsgCenter.beanUser != null) {
            String userName = MsgCenter.beanUser.getName();
            String call = null;
            if (userName != null) {
                call = userName.substring(0, 1);
                if (MsgCenter.beanUser.getSex().equals("男")) {
                    call += " 先生";
                } else {
                    call += " 女士";
                }
                call += "，你好啊!";
            }
            name.setText(call);
        }

        menu_lv.setAdapter(new LeftMenuAdapter());
        menu_lv.setOnItemClickListener(new ItemClickListener());


        return menu;
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            switch (position) {
                case 0:
                    //地址管理
                    intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.setting.address.AddressManageActivity");
                    activity.startActivity(intent);
                    break;
                case 1:
                    //订单
                    intent.setClassName("com.edu.wmhxa.sskd", "com.edu.wmhxa.sskd.activity.order.MyOrderActivity");
                    activity.startActivity(intent);
                    break;
                case 2:
                    //设置

                    break;
            }
        }
    }

    public class LeftMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            if (convertView == null) {
                view = View.inflate(activity.getApplicationContext(), R.layout.item_leftmenu, null);
            } else {
                view = convertView;
            }

            TextView tv = (TextView) view.findViewById(R.id.leftmenu_tv_item);
            tv.setText(itemList.get(position));

            return view;
        }
    }

}
