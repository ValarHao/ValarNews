package com.valarhao.valarnews.module.main;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private final MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        mMainView = checkNotNull(mainView);
        mMainView.setPresenter(this);
    }
}
