package com.valarhao.valarnews.module.zhihu.module.theme;

import android.content.Context;
import android.content.Intent;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.app.App;
import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;
import com.valarhao.valarnews.module.zhihu.common.RecyclerItem;
import com.valarhao.valarnews.module.zhihu.common.RecyclerTabAdapter;
import com.valarhao.valarnews.module.zhihu.module.theme.child.ThemeChildActivity;

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
    public void clickItem(Context context, RecyclerTabAdapter adapter, int position) {
        RecyclerItem recyclerItem = adapter.getRecyclerItem(position);
        Intent intent = ThemeChildActivity.newIndexIntent(context, recyclerItem.getId());
        context.startActivity(intent);
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
                        String str = App.getInstance().getResources().getString(R.string.network_error_message);
                        mThemeView.showError(str);
                    }
                });
    }
}
