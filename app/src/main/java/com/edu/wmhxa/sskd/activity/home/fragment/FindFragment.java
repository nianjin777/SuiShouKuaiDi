package com.edu.wmhxa.sskd.activity.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.edu.wmhxa.sskd.R;
import com.edu.wmhxa.sskd.control.MsgCenter;


/**
 * Created by Administrator on 2017/7/19.
 */

public class FindFragment extends Fragment {

    private static final String url = "";
    private static String data = "";

    private View view;
    private WebView find_wv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);

        findViewById();
        data = "?useraccount=" + MsgCenter.beanUser.getUsername() + "&username=" + MsgCenter.beanUser.getName();

        find_wv.getSettings().setJavaScriptEnabled(true);
        find_wv.setWebViewClient(new WebViewClient());
        find_wv.loadUrl("https://www.baidu.com");

        return view;
    }

    private void findViewById() {
        find_wv = (WebView) view.findViewById(R.id.find_wv);
    }
}
