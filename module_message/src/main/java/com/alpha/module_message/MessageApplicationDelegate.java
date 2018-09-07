package com.alpha.module_message;

import com.alpha.module_common.base.IApplicationDelegate;
import com.alpha.module_common.manager.FragmentPageManager;

public class MessageApplicationDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        FragmentPageManager.getInstance().addFragment(0, MessageFragment.getInstance());

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
