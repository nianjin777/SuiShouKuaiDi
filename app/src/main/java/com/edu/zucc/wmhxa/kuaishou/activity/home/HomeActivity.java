package com.edu.zucc.wmhxa.kuaishou.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.zucc.wmhxa.kuaishou.R;
import com.edu.zucc.wmhxa.kuaishou.activity.home.menu.LeftMenu;
import com.edu.zucc.wmhxa.kuaishou.util.SysApplication;
import com.edu.zucc.wmhxa.kuaishou.activity.home.fragment.*;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends FragmentActivity implements View.OnClickListener {

    private String TAG = "HomeActivity";
    private SlidingMenu menu;
    private ViewPager mViewPager;
    private AlphaTabsIndicator alphaTabsIndicator;
    private View home_title4;
    private View home_title2;
    private TextView title1_tv;
    public TextView title2_tv_accept;
    public TextView title2_tv_issue;

    public static HomeActivity activity = null;
    public List<Fragment> fragmentsList = new ArrayList<>();
    private long mExitTime;
    public BottomAdapter bottomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SysApplication.getInstance().addActivity(this);
        activity = this;

        menu = new LeftMenu(this).getLeftMenu();
        findViewById();
        setListener();

        //把碎片加入List中
        fragmentsList.add(new AccpetHallFragment());
        fragmentsList.add(new MsgFragment());
        fragmentsList.add(new BookFragment());
        fragmentsList.add(new FindFragment());
        bottomAdapter = new BottomAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(bottomAdapter);
        mViewPager.addOnPageChangeListener(bottomAdapter);
        alphaTabsIndicator.setViewPager(mViewPager);

        //设置假数据
        alphaTabsIndicator.getTabView(0).showNumber(6);
        alphaTabsIndicator.getTabView(1).showNumber(888);
        alphaTabsIndicator.getTabView(2).showNumber(88);
        alphaTabsIndicator.getTabView(3).showPoint();
    }

    private void findViewById() {
        alphaTabsIndicator = (AlphaTabsIndicator) findViewById(R.id.alphaIndicator);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        home_title4 = findViewById(R.id.home_title4);
        title1_tv = (TextView) home_title4.findViewById(R.id.title1_tv);
        home_title2 = findViewById(R.id.home_title2);
        title2_tv_accept = (TextView) home_title2.findViewById(R.id.title2_tv_accept);
        title2_tv_issue = (TextView) home_title2.findViewById(R.id.title2_tv_issue);

    }

    private void setListener() {
        title2_tv_accept.setOnClickListener(this);
        title2_tv_issue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title2_tv_accept:
                Log.i(TAG, "点击接受任务");
                title2_tv_accept.setBackground(getDrawable(R.color.white));
                title2_tv_issue.setBackground(getDrawable(R.color.head));

                //修改页面
                fragmentsList.set(0, AccpetHallFragment.getInstanceFragment());
                bottomAdapter.notifyDataSetChanged();

                break;

            case R.id.title2_tv_issue:
                Log.i(TAG, "点击发布任务");
                title2_tv_accept.setBackground(getDrawable(R.color.head));
                title2_tv_issue.setBackground(getDrawable(R.color.white));

                //修改页面
                fragmentsList.set(0, IssueHallFragment.getInstanceFragment());
                bottomAdapter.notifyDataSetChanged();

                break;
        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            SysApplication.getInstance().exit();
        }
    }

    public class BottomAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

        public BottomAdapter(FragmentManager fm) {
            super(fm);
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /*
            arg0 :当前页面，及你点击滑动的页面
            arg1:当前页面偏移的百分比
            arg2:当前页面偏移的像素位置
         */
        }

        @Override
        public void onPageSelected(int position) {
            //此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position(位置编号)。
            if (position == 0) {
                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                home_title4.setVisibility(View.GONE);
                home_title2.setVisibility(View.VISIBLE);
            } else {
                menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                home_title4.setVisibility(View.VISIBLE);
                home_title2.setVisibility(View.GONE);
                switch (position) {
                    case 1:
                        title1_tv.setText("消息");
                        break;
                    case 2:
                        title1_tv.setText("通讯录");
                        break;
                    case 3:
                        title1_tv.setText("发现");
                        break;
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //有三种状态(0，1，2)。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
        }


    }
}
