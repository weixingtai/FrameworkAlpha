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

/**
 * 命名格式：
 * 类：直接使用类名
 * 布局文件：common_类的类型_名称
 * 控件：控件类型_布局名称_控件名称
 * 资源文件：common_类_名称
 *
 *
 */
public class BaseActivity extends FragmentActivity implements IOnDataListener {

    protected Context mContext;
    private AsyncTaskManager mAsyncTaskManager;
    private ViewFlipper mContentView;
    private RelativeLayout rlTopBar;
    private Button btnTopBarLeft,btnTopBarRight;
    private TextView tvTopBarMiddle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.common_activity_base);
        mContext = this;
        initView();
        //初始化异步框架
        mAsyncTaskManager = AsyncTaskManager.getInstance(mContext.getApplicationContext());
        //Activity管理
        ActivityPageManager.getInstance().addActivity(this);


    }
    private void initView(){
        rlTopBar = super.findViewById(R.id.common_base_rl_top_bar);
        mContentView = super.findViewById(R.id.common_base_vf_content_container);
        btnTopBarLeft = super.findViewById(R.id.common_base_btn_top_bar_left);
        btnTopBarLeft = super.findViewById(R.id.common_base_btn_top_bar_right);
        tvTopBarMiddle = super.findViewById(R.id.common_base_tv_top_bar_middle);

    }

    public void setTopBarVisibility(int visibility){
        rlTopBar.setVisibility(visibility);
    }

    public void setMenuVisibility(int visibility){
        btnTopBarLeft.setVisibility(visibility);
    }

    public void setLoginBtnVisibility(int visibility){
        btnTopBarRight.setVisibility(visibility);
    }

    public void setTitleVisibility(int visibility){
        tvTopBarMiddle.setVisibility(visibility);
    }

    /**
     * 设置标题
     *
     * @param titleId 标题文本资源
     */
    public void setTitle(int titleId) {
        tvTopBarMiddle.setText(getString(titleId));
    }


    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mContentView.addView(view, lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
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
