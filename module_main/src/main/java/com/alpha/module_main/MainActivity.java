package com.alpha.module_main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alpha.module_common.base.BaseActivity;

import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
