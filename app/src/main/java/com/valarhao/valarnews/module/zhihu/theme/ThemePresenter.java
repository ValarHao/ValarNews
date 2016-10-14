package com.valarhao.valarnews.module.zhihu.theme;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class ThemePresenter implements ThemeContract.Presenter {

    private static final String TAG = ThemePresenter.class.getSimpleName();

    private final ThemeContract.View mThemeView;

    public ThemePresenter(ThemeContract.View themeView) {
        mThemeView = checkNotNull(themeView);
        mThemeView.setPresenter(this);
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "ThemePresenter init!");
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

        RetrofitHelper.sZhihuApi.getTheme()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ThemeJson>() {
                    @Override
                    public void call(ThemeJson themeJson) {
                        mThemeView.showRecyclerView(themeJson.getThemes());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mThemeView.showError("加载数据失败，请检查网络连接！");
                    }
                });
    }
}
