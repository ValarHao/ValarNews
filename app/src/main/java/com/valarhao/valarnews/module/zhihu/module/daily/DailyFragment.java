package com.valarhao.valarnews.module.zhihu.module.daily;

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
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.zhihu.RecyclerItem;
import com.valarhao.valarnews.module.zhihu.RecyclerListAdapter;

import java.util.List;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

@SuppressLint("ValidFragment")
public class DailyFragment extends BaseFragment implements DailyContract.View {

    private DailyContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerListAdapter mRecyclerAdapter;
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
        return inflater.inflate(R.layout.zhihu_fragment_common, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new DailyPresenter(this);

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.zhihuRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(recyclerLayoutManager);
        mRecyclerAdapter = new RecyclerListAdapter(this.getContext());
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setOnItemClickListener(new RecyclerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPresenter.clickItem(getActivity(), mRecyclerAdapter, position);
            }
        });

        mRecyclerAdapter.setOnBottomListener(new RecyclerListAdapter.OnBottomListener() {
            @Override
            public void OnBottom() {
                mPresenter.bottomRefresh();
            }
        });

        mSwipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.zhihuSwipeRefresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.swipeRefresh();
            }
        });
        Utils.showSwipeRefresh(mSwipeRefresh);

        mPresenter.init();
    }

    @Override
    public void showRecyclerView(List<DailyJson.Daily> dailies) {
        mSwipeRefresh.setRefreshing(false);
        mRecyclerAdapter.clear();
        for (DailyJson.Daily daily : dailies) {
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.setImgLink(daily.getImages().get(0));
            recyclerItem.setTitle(daily.getTitle());
            recyclerItem.setId(daily.getId());
            mRecyclerAdapter.addRecyclerItem(recyclerItem);
        }
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAddRecyclerView(List<DailyJson.Daily> dailies) {
        mSwipeRefresh.setRefreshing(false);
        for (DailyJson.Daily daily : dailies) {
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.setImgLink(daily.getImages().get(0));
            recyclerItem.setTitle(daily.getTitle());
            mRecyclerAdapter.addRecyclerItem(recyclerItem);
        }
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        mSwipeRefresh.setRefreshing(false);
        Utils.showSnackbar(mRecyclerView, msg);
    }
}
