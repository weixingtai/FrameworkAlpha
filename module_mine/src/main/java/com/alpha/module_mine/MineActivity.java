package com.alpha.module_mine;

import android.app.FragmentManager;
import android.os.Bundle;

import com.alpha.module_common.base.BaseActivity;
import com.alpha.module_common.manager.FragmentPageManager;

public class MineActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentPageManager.getInstance().addFragment(0,MineFragment.getInstance());
    }
}
