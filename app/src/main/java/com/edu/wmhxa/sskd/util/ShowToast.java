package com.edu.wmhxa.sskd.util;

import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/4.
 */

public class ShowToast {
    public static void show(String info) {
        Toast.makeText(MyApplication.getContext().getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }
}
