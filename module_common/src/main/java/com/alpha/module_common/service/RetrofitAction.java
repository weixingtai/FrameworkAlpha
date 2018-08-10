package com.alpha.module_common.service;

import android.content.Context;

import com.alpha.module_common.model.base.BaseResponse;
import com.alpha.module_common.model.response.ConfigData;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class RetrofitAction extends RetrofitManager {

    /**
     * 构造方法
     * @param mContext
     */
    public RetrofitAction(Context mContext) {
        super(mContext);
    }

    /**
     * 获取配置信息接口
     * @return
     */
    public Call<BaseResponse<List<ConfigData>>> getConfig() {
        HashMap params = getRequestParams();
        return apiService.getConfig(params);
    }

}
