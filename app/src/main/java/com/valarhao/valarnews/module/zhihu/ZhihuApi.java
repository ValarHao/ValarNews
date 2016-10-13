package com.valarhao.valarnews.module.zhihu;

import com.valarhao.valarnews.module.zhihu.daily.DailyJson;
import com.valarhao.valarnews.module.zhihu.detail.DetailInfoJson;
import com.valarhao.valarnews.module.zhihu.hot.HotJson;
import com.valarhao.valarnews.module.zhihu.section.SectionJson;
import com.valarhao.valarnews.module.zhihu.theme.ThemeJson;

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

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<DetailInfoJson> getDetailInfo(@Path("id") int id);

    /**
     * 主题日报
     */
    @GET("themes")
    Observable<ThemeJson> getTheme();

    /**
     * 专栏日报
     */
    @GET("sections")
    Observable<SectionJson> getSection();

    /**
     * 热门日报
     */
    @GET("news/hot")
    Observable<HotJson> getHot();
}
