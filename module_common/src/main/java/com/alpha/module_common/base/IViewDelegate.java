package com.alpha.module_common.base;


import android.support.annotation.Keep;
import android.view.View;

/**
 * View代理接口
 *
 * 根据模块不同加载获取不同界面
 */
@Keep
public interface IViewDelegate {

    BaseFragment getFragment(String name);

    View getView(String name);

}
