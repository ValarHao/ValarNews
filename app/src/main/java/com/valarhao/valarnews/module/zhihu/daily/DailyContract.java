package com.valarhao.valarnews.module.zhihu.daily;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;
import com.valarhao.valarnews.module.main.RecyclerListAdapter;

import java.util.List;

public interface DailyContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<DailyJson.Story> stories);
        void showAddRecyclerView(List<DailyJson.Story> stories);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(FragmentActivity activity, RecyclerListAdapter adapter, int position);
        void bottomRefresh();
    }
}
