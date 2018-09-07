package com.alpha.module_message;

import android.view.View;

import com.alpha.module_common.base.BaseFragment;
import com.alpha.module_common.base.IViewDelegate;

public class MessageViewDelegate implements IViewDelegate {
    @Override
    public BaseFragment getFragment(String name) {
        return MessageFragment.getInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
