package com.valarhao.valarnews.module.zhihu.section;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

import java.util.List;

public interface SectionContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<SectionJson.Section> sections);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(FragmentActivity activity);
    }
}
