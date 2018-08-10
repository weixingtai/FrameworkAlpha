package com.alpha.module_common.service;

import com.alpha.module_common.model.base.BaseResponse;
import com.alpha.module_common.model.response.ConfigData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface IApiService {

    @GET("/app/sys/getConfig")
    Call<BaseResponse<List<ConfigData>>> getConfig(@QueryMap Map<String, String> options);


    @GET("/app/circle/getCircleTypeList")
    Call<BaseResponse<List<ConfigData>>> getCircleTypeList(@Query("patientId") String patientId);
}
