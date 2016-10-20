package com.valarhao.valarnews.module.zhihu.module.section;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class SectionPresenter implements SectionContract.Presenter {

    private static final String TAG = SectionPresenter.class.getSimpleName();

    private final SectionContract.View mSectionView;

    public SectionPresenter(SectionContract.View sectionView) {
        mSectionView = checkNotNull(sectionView);
        mSectionView.setPresenter(this);
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "SectionPresenter init!");
        getSection();
    }

    @Override
    public void swipeRefresh() {
        getSection();
    }

    @Override
    public void clickItem(FragmentActivity activity) {

    }

    /**
     * 加载专栏日报
     */
    private void getSection() {

        RetrofitHelper.sZhihuApi.getSection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SectionJson>() {
                    @Override
                    public void call(SectionJson sectionJson) {
                        mSectionView.showRecyclerView(sectionJson.getSections());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSectionView.showError("加载数据失败，请检查网络连接！");
                    }
                });
    }
}
