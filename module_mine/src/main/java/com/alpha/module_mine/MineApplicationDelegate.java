package com.alpha.module_mine;

import com.alpha.module_common.base.IApplicationDelegate;
import com.alpha.module_common.manager.FragmentPageManager;

public class MineApplicationDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        FragmentPageManager.getInstance().addFragment(0, MineFragment.getInstance());

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
