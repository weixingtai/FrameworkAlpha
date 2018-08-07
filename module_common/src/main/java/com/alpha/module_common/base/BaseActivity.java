package com.alpha.module_common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;

/**
 * <p>Activity基类 </p>
 *
 * @author 2016/12/2 17:33
 * @version V1.0.0
 */
@Keep
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
