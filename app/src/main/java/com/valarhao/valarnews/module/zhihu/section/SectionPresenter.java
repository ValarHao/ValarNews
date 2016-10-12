package com.valarhao.valarnews.module.zhihu.section;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.zhihu.ZhihuApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class SectionPresenter implements SectionContract.Presenter {

    private static final String TAG = SectionPresenter.class.getSimpleName();

    private final SectionContract.View mSectionView;
    private ZhihuApi mZhihuApi;

    public SectionPresenter(SectionContract.View sectionView) {
        mSectionView = checkNotNull(sectionView);
        mSectionView.setPresenter(this);
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "SectionPresenter init!");
        //初始化Retrofit的API接口
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mZhihuApi = retrofit.create(ZhihuApi.class);
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

        mZhihuApi.getSection()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SectionJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mSectionView.showError("加载专栏日报失败！！！");
                    }

                    @Override
                    public void onNext(SectionJson sectionJson) {
                        mSectionView.showRecyclerView(sectionJson.getDatas());
                    }
                });
    }
}
