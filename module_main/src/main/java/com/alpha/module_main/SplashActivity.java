package com.alpha.module_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alpha.module_common.Constants;
import com.alpha.module_common.async.HttpException;
import com.alpha.module_common.base.BaseActivity;
import com.alpha.module_common.manager.PreferencesManager;
import com.alpha.module_common.model.base.BaseResponse;
import com.alpha.module_common.model.response.ConfigData;
import com.alpha.module_common.service.RetrofitAction;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

@Route(path = "/main/SplashActivity")
public class SplashActivity extends BaseActivity {
    private final String TAG = SplashActivity.class.getSimpleName();

    private final int TEST_CODE_1 = 100;
    private Boolean pageDebug = true;//引导页面测试开关
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) == Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) {
            super.onCreate(savedInstanceState);
            finish();
            return;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_splash);
        setTopBarVisibility(View.GONE);

        request(TEST_CODE_1);
    }

    @Override
    public Object doInBackground(int requestCode) throws HttpException {
        try {
            RetrofitAction action = new RetrofitAction(mContext.getApplicationContext());
            switch (requestCode){
                case TEST_CODE_1:{
                    Call<BaseResponse<List<ConfigData>>> call = action.getConfig();
                    return call.execute().body();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException(e);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        if(result != null){
            switch (requestCode){
                case TEST_CODE_1:{
                    BaseResponse<List<ConfigData>> res = (BaseResponse<List<ConfigData>>)result;
                    if(res.isSucces()){
                        startMainPage();
                    }
                }
            }
        }
    }

    @Override
    public void onFailure(int requestCode, int state, Object result) {
        super.onFailure(requestCode, state, result);
        startMainPage();
    }

    public void startMainPage(){
        boolean isFirstRun = PreferencesManager.getInstance(mContext).get(Constants.IS_FIRST_RUN, true);
        if(isFirstRun||pageDebug){
            Intent intent = new Intent(mContext, GuideActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
