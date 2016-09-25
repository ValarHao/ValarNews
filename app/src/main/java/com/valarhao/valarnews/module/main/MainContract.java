package com.valarhao.valarnews.module.main;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showTabZhihu();
    }

    interface Presenter extends BasePresenter {

    }
}
