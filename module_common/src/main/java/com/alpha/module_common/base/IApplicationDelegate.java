package com.alpha.module_common.base;

import android.support.annotation.Keep;

/**
 * Application代理接口
 *
 * 根据模块不同加载不同处理方法
 */
@Keep
public interface IApplicationDelegate {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

}
