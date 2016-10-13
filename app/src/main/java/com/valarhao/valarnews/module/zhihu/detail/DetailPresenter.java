package com.valarhao.valarnews.module.zhihu.detail;

import com.valarhao.valarnews.common.util.LogUtil;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class DetailPresenter implements DetailContract.Presenter {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    private final DetailContract.View mDetailView;
    private final int mId;

    public DetailPresenter(DetailContract.View detailView, int id) {
        mDetailView = checkNotNull(detailView);
        mDetailView.setPresenter(this);
        mId = id;
    }
}
