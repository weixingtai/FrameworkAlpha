package com.alpha.module_common.okhttp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();

        //添加Agent
        Request request = original.newBuilder()
                .header("User-Agent", "Your-App-Name")
                .method(original.method(), original.body())
                .build();

        //添加token
        HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("token", "tokenValue")
                .build();

        request = request.newBuilder().url(httpUrl).build();

        return chain.proceed(request);
    }
}
