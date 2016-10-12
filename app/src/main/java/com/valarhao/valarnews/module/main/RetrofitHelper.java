package com.valarhao.valarnews.module.main;

import com.valarhao.valarnews.module.zhihu.ZhihuApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static ZhihuApi sZhihuApi = null;

    public static void init() {
        initZhihuApi();
    }

    private static void initZhihuApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        sZhihuApi = retrofit.create(ZhihuApi.class);
    }
}
