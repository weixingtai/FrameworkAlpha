package com.alpha.module_mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpha.module_common.base.BaseFragment;

public class MineFragment extends BaseFragment {
    public MineFragment(){

    }

    public static MineFragment getInstance(){
        return new MineFragment();
    }

    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mine,container,false);
    }
}
