package com.valarhao.valarnews.module.main;

import com.valarhao.valarnews.module.zhihu.common.ZhihuApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static final int TIMEOUT = 10;

    private static OkHttpClient sClient;
    public static ZhihuApi sZhihuApi = null;

    public static void init() {
        sClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
        initZhihuApi();
    }

    private static void initZhihuApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.URL)
                .client(sClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        sZhihuApi = retrofit.create(ZhihuApi.class);
    }
}
