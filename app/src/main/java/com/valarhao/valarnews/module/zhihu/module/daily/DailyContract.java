package com.valarhao.valarnews.module.zhihu.module.daily;

import android.content.Context;
import android.support.annotation.StringRes;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;
import com.valarhao.valarnews.module.zhihu.common.RecyclerListAdapter;

import java.util.List;

public interface DailyContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<DailyJson.Daily> dailies);
        void showAddRecyclerView(List<DailyJson.Daily> dailies);
        void showError(@StringRes int resId);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(Context context, RecyclerListAdapter adapter, int position);
        void bottomRefresh();
    }
}
