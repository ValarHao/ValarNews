package com.valarhao.valarnews.module.zhihu.daily;

import com.valarhao.valarnews.common.util.LogUtil;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class DailyPresenter implements DailyContract.Presenter {

    private static final String TAG = DailyPresenter.class.getSimpleName();

    private final DailyContract.View mDailyView;

    public DailyPresenter(DailyContract.View mainView) {
        mDailyView = checkNotNull(mainView);
        mDailyView.setPresenter(this);
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "DailyPresenter init!");
    }

    @Override
    public void swipeRefresh() {

    }
}
