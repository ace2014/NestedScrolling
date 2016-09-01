package com.pzl.nestedscrolling_api21.widget;

import android.content.Context;

/**
 * Created by zl.peng on 2016/8/17 22:42.
 */
public class Toast {
    static android.widget.Toast toast;

    public static void show(String msg, Context ct) {
        if (toast == null)
            toast = android.widget.Toast.makeText(ct, "", android.widget.Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
}
