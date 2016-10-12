package com.valarhao.valarnews.module.zhihu.daily;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

import java.util.List;

public interface DailyContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<DailyJson.Daily> dailies);
        void showAddRecyclerView(List<DailyJson.Daily> dailies);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(FragmentActivity activity);
        void bottomRefresh();
    }
}
