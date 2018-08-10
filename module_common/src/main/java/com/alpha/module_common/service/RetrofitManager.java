package com.alpha.module_common.service;

import android.content.Context;

import com.alpha.module_common.Constants;
import com.alpha.module_common.okhttp.OkHttpUtils;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class RetrofitManager {

    protected Retrofit retrofit;
    protected IApiService apiService;

    /**
     * 构造方法
     */
    public RetrofitManager(Context context) {
        retrofit = new Retrofit.Builder()
                .client(OkHttpUtils.getInstance(context).getOkHttpClient())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.DOMAIN)
                .build();

        //初始化接口类
        apiService = retrofit.create(IApiService.class);
    }


    /**
     * 获取处理后的RequestParams对象
     * @return
     */
    public HashMap getRequestParams(){
        HashMap params = new HashMap();
        params.put("app_id", "2016789168");
        params.put("model", "android");
        params.put("version", "1.0");
        return params;
    }


    public void toSubscribe(Observable observable, rx.Subscriber subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
