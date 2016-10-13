package com.valarhao.valarnews.module.zhihu.hot;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class HotPresenter implements HotContract.Presenter {

    private static final String TAG = HotPresenter.class.getSimpleName();

    private final HotContract.View mHotView;

    public HotPresenter(HotContract.View hotView) {
        mHotView = checkNotNull(hotView);
        mHotView.setPresenter(this);
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "HotPresenter init!");
        getHot();
    }

    @Override
    public void swipeRefresh() {
        getHot();
    }

    @Override
    public void clickItem(FragmentActivity activity) {

    }

    /**
     * 加载热门日报
     */
    private void getHot() {

        RetrofitHelper.sZhihuApi.getHot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mHotView.showError("加载失败，请检查网络连接！");
                    }

                    @Override
                    public void onNext(HotJson hotJson) {
                        mHotView.showRecyclerView(hotJson.getHots());
                    }
                });
    }
}
