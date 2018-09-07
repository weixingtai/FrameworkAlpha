package com.alpha.module_home;

import android.view.View;

import com.alpha.module_common.base.BaseFragment;
import com.alpha.module_common.base.IViewDelegate;

public class HomeViewDelegate implements IViewDelegate {
    @Override
    public BaseFragment getFragment(String name) {
        return HomeFragment.getInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
