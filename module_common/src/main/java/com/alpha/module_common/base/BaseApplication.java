package com.alpha.module_common.base;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alpha.module_common.utils.ClassUtils;
import com.alpha.module_common.utils.CommonUtils;
import com.alpha.module_common.utils.NLog;

import java.util.List;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 */
public class BaseApplication extends Application {
    private final String TAG = BaseApplication.class.getSimpleName();
    public static final String ROOT_PACKAGE = "com.alpha";
    private List<IApplicationDelegate> mAppDelegateList;

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
    }

    private void initConfig() {
        //初始化ARouter
        ARouter.init(this);

        //初始化debug模式
        String flag = CommonUtils.getProperty(getApplicationContext(), "debug");
        if (!TextUtils.isEmpty(flag)) {
            Boolean isDebug = Boolean.parseBoolean(flag);
            NLog.setDebug(true);
            NLog.d(TAG, "isDebug: " + isDebug);
        }
    }
}
