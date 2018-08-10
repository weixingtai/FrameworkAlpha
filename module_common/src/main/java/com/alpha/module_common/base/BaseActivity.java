package com.alpha.module_common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alpha.module_common.R;
import com.alpha.module_common.async.AsyncTaskManager;
import com.alpha.module_common.async.HttpException;
import com.alpha.module_common.async.IOnDataListener;
import com.alpha.module_common.manager.ActivityPageManager;
import com.alpha.module_common.utils.NToast;

public class BaseActivity extends FragmentActivity implements IOnDataListener {

    protected Context mContext;
    private AsyncTaskManager mAsyncTaskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //初始化异步框架
        mAsyncTaskManager = AsyncTaskManager.getInstance(mContext.getApplicationContext());
        //Activity管理
        ActivityPageManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityPageManager.getInstance().removeActivity(this);
        mContext = null;
    }

    /**
     * 发送请求（需要检查网络）
     *
     * @param requestCode 请求码
     */
    public void request(int requestCode){
        mAsyncTaskManager.request(requestCode, this);
    }

    /**
     * 发送请求
     *
     * @param requestCode 请求码
     * @param isCheckNetwork 是否需检查网络，true检查，false不检查，主要是用于有时候网络请求从缓存里面取的
     */
    public void request(int requestCode, boolean isCheckNetwork){
        mAsyncTaskManager.request(requestCode, isCheckNetwork, this);
    }


    @Override
    public Object doInBackground(int requestCode) throws HttpException {
        return null;
    }

    @Override
    public boolean onIntercept(int requestCode, Object result) {
        return false;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
    }

    @Override
    public void onFailure(int requestCode, int state, Object result) {
        switch(state){
            //网络不可用给出提示
            case AsyncTaskManager.HTTP_NULL_CODE:
                NToast.shortToast(mContext, R.string.common_network_unavailable);
                break;

            //网络有问题给出提示
            case AsyncTaskManager.HTTP_ERROR_CODE:
                NToast.shortToast(mContext, R.string.common_network_error);
                break;

            //请求有问题给出提示
            case AsyncTaskManager.REQUEST_ERROR_CODE:
                NToast.shortToast(mContext, R.string.common_request_error);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
