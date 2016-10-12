package com.valarhao.valarnews.module.zhihu.theme;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
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
                .subscribe(new Observer<ThemeJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mThemeView.showError("加载主题日报失败！！！");
                    }

                    @Override
                    public void onNext(ThemeJson themeJson) {
                        mThemeView.showRecyclerView(themeJson.getThemes());
                    }
                });
    }
}
