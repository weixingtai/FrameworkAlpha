package com.alpha.module_common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class CommonUtils {

    /** 网络类型 **/
    public static final int NET_TYPE_WIFI = 0x01;
    public static final int NET_TYPE_CMWAP = 0x02;
    public static final int NET_TYPE_CMNET = 0x03;


    /**
     * 根据key获取config.properties里面的值
     *
     * @param context 上下文
     * @param key 关键字
     * @return 属性
     */
    public static String getProperty(Context context, String key){
        try {
            Properties props = new Properties();
            Log.d("weixingtai",""+context.getAssets().toString());
            InputStream input = context.getAssets().open("config.properties");
            if (input != null) {
                props.load(input);
                return props.getProperty(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 检测网络是否可用
     *
     * @return 网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = null;
        if (cm != null) {
            ni = cm.getActiveNetworkInfo();
        }
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase(Locale.getDefault()).equals("cmnet")) {
                    netType = NET_TYPE_CMNET;
                } else {
                    netType = NET_TYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NET_TYPE_WIFI;
        }
        return netType;
    }


    /**
     * 判断SDCard是否存在,并可写
     *
     * @return SD卡是否存在并可写
     */
    public static boolean checkSDCard(){
        String  flag = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(flag);
    }

    /**
     * 获取屏幕宽度
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕显示信息对象
     * @param context 上下文
     * @return 屏幕显示信息对象
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        return context.getResources().getDisplayMetrics();
    }

    /**
     * dp转pixel
     *
     * @param dp dp值
     * @param context 上下文
     * @return 像素值
     */
    public static float dpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * pixel转dp
     *
     * @param px px
     * @param context 上下文
     * @return dp值
     */
    public static float pixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    /**
     * 短信分享
     *
     * @param mContext 上下文
     * @param smsText 短信分享内容
     * @return 是否分享成功
     */
    public static Boolean sendSms(Context mContext, String smsText){
        Uri smsToUri = Uri.parse("smsto:");
        Intent mIntent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", smsText);
        mContext.startActivity(mIntent);
        return null;
    }

    /**
     * 邮件分享
     *
     * @param mContext 上下文
     * @param title 邮件的标题
     * @param text 邮件的内容
     */
    public static void sendMail(Context mContext, String title, String text){
        // 调用系统发邮件
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        // 设置文本格式
        emailIntent.setType("text/plain");
        // 设置对方邮件地址
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, "");
        // 设置标题内容
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        // 设置邮件文本内容
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        mContext.startActivity(Intent.createChooser(emailIntent, "Choose Email Client"));
    }

    /**
     * 隐藏软键盘
     * @param activity 当前activity
     */
    public static void hideKeyboard(Activity activity) {
        if(activity != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null && imm.isActive() &&activity.getCurrentFocus()!=null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     * @param activity 当前activity
     */
    public static void showKeyboard(Activity activity) {
        if(activity != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null && !imm.isActive() && activity.getCurrentFocus() != null) {
                imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 是否横屏
     * @param context 上下文
     * @return true为横屏，false为竖屏
     */
    public static boolean isLandscape(Context context){
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 判断是否是平板
     *
     * 这个方法是从 Google I/O App for Android 的源码里找来的，非常准确。
     * @param context 上下文
     * @return 是否平板
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
