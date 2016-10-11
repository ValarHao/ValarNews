package com.valarhao.valarnews.module.zhihu.theme;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.zhihu.ZhihuApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class ThemePresenter implements ThemeContract.Presenter {

    private static final String TAG = ThemePresenter.class.getSimpleName();

    private final ThemeContract.View mThemeView;
    private ZhihuApi mZhihuApi;

    public ThemePresenter(ThemeContract.View themeView) {
        mThemeView = checkNotNull(themeView);
        mThemeView.setPresenter(this);
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "ThemePresenter init!");
        //初始化Retrofit的API接口
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mZhihuApi = retrofit.create(ZhihuApi.class);
        getTheme();
    }

    @Override
    public void swipeRefresh() {
        getTheme();
    }

    @Override
    public void clickItem(FragmentActivity activity) {

    }

    /**
     * 加载主题日报
     */
    private void getTheme() {

        mZhihuApi.getTheme()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ThemeJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mThemeView.showError("加载最新日报失败！！！");
                    }

                    @Override
                    public void onNext(ThemeJson themeJson) {
                        mThemeView.showRecyclerView(themeJson.getOthers());
                    }
                });
    }
}
