package com.valarhao.valarnews.module.zhihu.daily;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

public interface DailyContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
    }
}
