package com.alpha.module_common.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class NToast {

    public static void shortToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    public static void shortToast(Context context, String text) {
        if(!TextUtils.isEmpty(text) && !"".equals(text.trim())){
            showToast(context, text, Toast.LENGTH_SHORT);
        }
    }

    public static void longToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }

    public static void longToast(Context context, String text) {
        if(!TextUtils.isEmpty(text) && !"".equals(text.trim())){
            showToast(context, text, Toast.LENGTH_LONG);
        }
    }

    private static void showToast(Context context, int resId, int duration) {
        if (context == null){
            return;
        }
        if (context instanceof Activity) {
            if(((Activity) context).isFinishing()) {
                return;
            }
        }
        String text = context.getString(resId);
        showToast(context, text, duration);
    }

    private static void showToast(Context context, String text, int duration) {
        if (context == null){
            return;
        }
        if (context instanceof Activity) {
            if(((Activity) context).isFinishing()) {
                return;
            }
        }
        if(!TextUtils.isEmpty(text) && !"".equals(text.trim())){
            Toast.makeText(context, text, duration).show();
        }
    }
}
