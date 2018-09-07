package com.alpha.module_message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alpha.module_common.base.BaseFragment;

public class MessageFragment extends BaseFragment {

    public MessageFragment(){

    }
    public static MessageFragment getInstance(){
        return new MessageFragment();
    }

    @Override
    public View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_message,container,false);
    }
}
