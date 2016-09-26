package com.valarhao.valarnews.module.zhihu.daily;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.base.BaseFragment;
import com.valarhao.valarnews.module.main.RecyclerListAdapter;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

@SuppressLint("ValidFragment")
public class DailyFragment extends BaseFragment implements DailyContract.View {

    private static final String TAG = DailyFragment.class.getSimpleName();

    private DailyContract.Presenter mPresenter;
    private RecyclerListAdapter mRecyclerListAdapter;
    private SwipeRefreshLayout mSwipeRefresh;

    public DailyFragment(String title) {
        super(title);
    }

    @Override
    public void setPresenter(DailyContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new DailyPresenter(this);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        mRecyclerListAdapter = new RecyclerListAdapter(this.getContext());
        recyclerView.setAdapter(mRecyclerListAdapter);

        mSwipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.swipeRefresh();
            }
        });

        mPresenter.init();
    }
}
