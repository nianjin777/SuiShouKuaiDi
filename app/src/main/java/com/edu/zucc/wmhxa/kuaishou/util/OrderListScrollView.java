package com.edu.zucc.wmhxa.kuaishou.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/26.
 */

public class OrderListScrollView extends ScrollView {

    View v1;
    View v2;
    View v3;
    View vmove1;
    View vmove2;
    View vmove3;

    public OrderListScrollView(Context context) {
        super(context);
        init();
    }

    public OrderListScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OrderListScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    private void init() {
        vmove1 = findViewById(R.id.order_waitline);
        vmove2 = findViewById(R.id.order_ingline);
        vmove3 = findViewById(R.id.order_finishline);
//        v1 = findViewById(R.id.order_waitline2);
//        v2 = findViewById(R.id.order_ingline2);
//        v3 = findViewById(R.id.order_finishline2);
    }

    public void setV(View v1, View v2, View v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    @Override
    public void computeScroll() {
        int scrollY = getScrollY();
        if (scrollY >= vmove1.getTop() && !(scrollY >= vmove2.getTop()) && !(scrollY >= vmove3.getTop())) {
            v1.setVisibility(View.VISIBLE);
        } else {
            v1.setVisibility(View.GONE);
        }
        if (!(scrollY >= vmove1.getTop()) && (scrollY >= vmove2.getTop()) && !(scrollY >= vmove3.getTop())) {
            v2.setVisibility(View.VISIBLE);
        } else {
            v2.setVisibility(View.GONE);
        }
        if (!(scrollY >= vmove1.getTop()) && !(scrollY >= vmove2.getTop()) && (scrollY >= vmove3.getTop())) {
            v3.setVisibility(View.VISIBLE);
        } else {
            v3.setVisibility(View.GONE);
        }
        super.computeScroll();
    }
}
