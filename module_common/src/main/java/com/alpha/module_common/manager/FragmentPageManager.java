package com.alpha.module_common.manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.alpha.module_common.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FragmentPageManager {

    private static List<BaseFragment> fragmentList;

    public static FragmentPageManager getInstance() {
        return ViewManagerHolder.sInstance;
    }

    private static class ViewManagerHolder {
        private static final FragmentPageManager sInstance = new FragmentPageManager();
    }

    private FragmentPageManager() {
    }

    public void addFragment(int index, BaseFragment fragment) {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(index, fragment);
    }


    public BaseFragment getFragment(int index) {
        if (fragmentList != null) {
            return fragmentList.get(index);
        }
        return null;
    }


    public List<BaseFragment> getAllFragment() {
        if (fragmentList != null) {
            return fragmentList;
        }
        return null;
    }




}