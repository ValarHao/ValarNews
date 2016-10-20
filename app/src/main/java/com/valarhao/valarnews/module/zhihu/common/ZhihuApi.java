package com.valarhao.valarnews.module.zhihu.common;

import com.valarhao.valarnews.module.zhihu.module.daily.DailyJson;
import com.valarhao.valarnews.module.zhihu.common.detail.DetailExtraJson;
import com.valarhao.valarnews.module.zhihu.common.detail.DetailInfoJson;
import com.valarhao.valarnews.module.zhihu.common.detail.comment.DetailShortJson;
import com.valarhao.valarnews.module.zhihu.module.hot.HotJson;
import com.valarhao.valarnews.module.zhihu.module.section.SectionJson;
import com.valarhao.valarnews.module.zhihu.module.theme.ThemeJson;
import com.valarhao.valarnews.module.zhihu.module.theme.child.ThemeChildJson;

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
     * 主题日报
     */
    @GET("themes")
    Observable<ThemeJson> getTheme();

    /**
     * 主题日报详情
     */
    @GET("theme/{id}")
    Observable<ThemeChildJson> getThemeChild(@Path("id") int id);

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

    /**
     * 日报详情
     */
    @GET("news/{id}")
    Observable<DetailInfoJson> getDetailInfo(@Path("id") int id);

    /**
     * 日报额外信息
     */
    @GET("story-extra/{id}")
    Observable<DetailExtraJson> getDetailExtraInfo(@Path("id") int id);

    /**
     * 日报短评论
     */
    @GET("story/{id}/short-comments")
    Observable<DetailShortJson> getDetailShortInfo(@Path("id") int id);
}
