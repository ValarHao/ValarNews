package com.valarhao.valarnews.module.zhihu.module.hot;

import android.content.Context;

import com.valarhao.valarnews.common.base.BasePresenter;
import com.valarhao.valarnews.common.base.BaseView;
import com.valarhao.valarnews.module.zhihu.common.RecyclerListAdapter;

import java.util.List;

public interface HotContract {

    interface View extends BaseView<Presenter> {
        void showRecyclerView(List<HotJson.Hot> hots);
        void showError(String msg);
    }

    interface Presenter extends BasePresenter {
        void init();
        void swipeRefresh();
        void clickItem(Context context, RecyclerListAdapter adapter, int position);
    }
}
