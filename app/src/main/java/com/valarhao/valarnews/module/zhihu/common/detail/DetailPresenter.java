package com.valarhao.valarnews.module.zhihu.common.detail;

import android.content.Context;
import android.content.Intent;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.app.App;
import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;
import com.valarhao.valarnews.module.zhihu.common.detail.comment.ShortCommentActivity;

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
        LogUtil.d(TAG, "DetailActivity init!" + mId);
        getDetailInfo(mId);
        getDetailExtra(mId);
    }

    @Override
    public void clickComment(Context context) {
        Intent intent = ShortCommentActivity.newIndexIntent(context, mId);
        context.startActivity(intent);
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
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        String str = App.getInstance().getResources().getString(R.string.network_error_message);
                        mDetailView.showError(str);
                    }
                });
    }

    /**
     * 加载日报额外信息
     * @param id
     */
    private void getDetailExtra(int id) {

        RetrofitHelper.sZhihuApi.getDetailExtraInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DetailExtraJson>() {
                    @Override
                    public void call(DetailExtraJson detailExtraJson) {
                        mDetailView.showDetailExtra(detailExtraJson);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        String str = App.getInstance().getResources().getString(R.string.network_error_message);
                        mDetailView.showError(str);
                    }
                });
    }
}
