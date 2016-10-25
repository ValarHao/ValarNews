package com.valarhao.valarnews.module.main;

import com.valarhao.valarnews.common.app.Constant;
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.zhihu.common.ZhihuApi;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static OkHttpClient sClient;
    public static ZhihuApi sZhihuApi = null;

    public static void init() {
        initOkhttp();
        initZhihuApi();
    }

    private static void initOkhttp() {
        File cacheFile = new File(Constant.CACHE_PATH);
        Cache cache = new Cache(cacheFile, Constant.CACHE_PATH_SIZE);

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Utils.isNetworkConnected()) {
                    request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (Utils.isNetworkConnected()) {
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + Constant.CACHE_NETWORK_CONNECTED_SAVE_TIME)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + Constant.CACHE_NETWORK_UNCONNECTED_SAVE_TIME)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        sClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(cacheInterceptor)
                .cache(cache)
                .connectTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .build();
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
