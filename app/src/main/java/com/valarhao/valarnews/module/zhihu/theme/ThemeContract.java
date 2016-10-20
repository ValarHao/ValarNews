package com.valarhao.valarnews.module.zhihu.theme;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

import java.util.List;

public interface ThemeContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<ThemeJson.Theme> themes);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(Context context, RecyclerAdapter adapter, int position);
    }
}
