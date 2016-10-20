package com.valarhao.valarnews.module.zhihu.common.detail;

import android.content.Context;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;

public interface DetailContract {

    interface View extends BaseView<Presenter> {
        void showDetailInfo(DetailInfoJson detailInfoJson);
        void showDetailExtra(DetailExtraJson detailExtraJson);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void init();
        void clickComment(Context context);
    }
}
