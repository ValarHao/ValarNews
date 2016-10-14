package com.valarhao.valarnews.module.zhihu.detail;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

public interface DetailContract {

    interface View extends BaseView<Presenter> {
        void showDetailInfo(DetailInfoJson detailInfoJson);
    }

    interface Presenter extends BasePresenter {
        void init();
    }
}
