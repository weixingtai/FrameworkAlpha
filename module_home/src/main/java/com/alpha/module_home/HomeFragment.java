package com.alpha.module_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpha.module_common.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    public HomeFragment(){

    }
    public static HomeFragment getInstance(){
        return new HomeFragment();
    }
    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home,container,false);
    }
}
