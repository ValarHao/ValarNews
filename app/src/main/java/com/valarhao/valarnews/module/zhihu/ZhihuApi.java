package com.valarhao.valarnews.module.zhihu;

import com.valarhao.valarnews.module.zhihu.daily.DailyJson;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ZhihuApi {

    String URL = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     */
    @GET("news/latest")
    Observable<DailyJson> getDailyLatest();

    /**
     * 之前日报
     */
    @GET("news/before/{date}")
    Observable<DailyJson> getDailyBefore(@Path("date") String date);
}
