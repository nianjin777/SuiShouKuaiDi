package com.edu.zucc.wmhxa.kuaishou.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.edu.zucc.wmhxa.kuaishou.R;

/**
 * Created by Administrator on 2017/7/19.
 */

public class FindFragment extends Fragment {

    private View view;
    private WebView find_wv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);

        findViewById();

        find_wv.loadUrl("https://m.weibo.cn/");
        find_wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                view.loadUrl("https://m.weibo.cn/");
                return true;
            }
        });

        return view;
    }

    private void findViewById() {
        find_wv = (WebView) view.findViewById(R.id.find_wv);
    }
}
