package com.valarhao.valarnews.module.zhihu.module.theme;

import android.content.Context;
import android.support.annotation.StringRes;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;
import com.valarhao.valarnews.module.zhihu.common.RecyclerTabAdapter;

import java.util.List;

public interface ThemeContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<ThemeJson.Theme> themes);
        void showError(@StringRes int resId);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(Context context, RecyclerTabAdapter adapter, int position);
    }
}
