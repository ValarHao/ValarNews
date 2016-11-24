package com.valarhao.valarnews.module.zhihu.module.hot;

import android.content.Context;
import android.content.Intent;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;
import com.valarhao.valarnews.module.zhihu.common.RecyclerItem;
import com.valarhao.valarnews.module.zhihu.common.RecyclerListAdapter;
import com.valarhao.valarnews.module.zhihu.common.detail.DetailActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
    public void clickItem(Context context, RecyclerListAdapter adapter, int position) {
        RecyclerItem recyclerItem = adapter.getRecyclerItem(position);
        Intent intent = DetailActivity.newIndexIntent(context, recyclerItem.getId());
        context.startActivity(intent);
    }

    /**
     * 加载热门日报
     */
    private void getHot() {

        RetrofitHelper.sZhihuApi.getHot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HotJson>() {
                    @Override
                    public void call(HotJson hotJson) {
                        mHotView.showRecyclerView(hotJson.getHots());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mHotView.showError(R.string.network_error_message);
                    }
                });
    }
}
