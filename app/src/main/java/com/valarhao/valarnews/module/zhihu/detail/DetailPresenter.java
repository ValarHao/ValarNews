package com.valarhao.valarnews.module.zhihu.detail;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

    @Override
    public void init() {
        LogUtil.d(TAG, "DetailActivity init!");
        getDetailInfo(mId);
    }

    /**
     * 加载日报详情
     * @param id
     */
    private void getDetailInfo(int id) {

        RetrofitHelper.sZhihuApi.getDetailInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DetailInfoJson>() {
                    @Override
                    public void call(DetailInfoJson detailInfoJson) {
                        mDetailView.showDetailInfo(detailInfoJson);
                    }
                });
    }
}
