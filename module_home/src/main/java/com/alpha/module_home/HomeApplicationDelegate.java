package com.alpha.module_home;

import com.alpha.module_common.base.IApplicationDelegate;
import com.alpha.module_common.manager.FragmentPageManager;

public class HomeApplicationDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        FragmentPageManager.getInstance().addFragment(0, HomeFragment.getInstance());
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
