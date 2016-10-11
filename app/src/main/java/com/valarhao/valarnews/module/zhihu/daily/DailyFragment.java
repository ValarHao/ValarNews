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
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.main.RecyclerItem;
import com.valarhao.valarnews.module.main.RecyclerListAdapter;

import java.util.List;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

@SuppressLint("ValidFragment")
public class DailyFragment extends BaseFragment implements DailyContract.View {

    private static final String TAG = DailyFragment.class.getSimpleName();

    private DailyContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
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

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(recyclerLayoutManager);
        mRecyclerListAdapter = new RecyclerListAdapter(this.getContext());
        mRecyclerView.setAdapter(mRecyclerListAdapter);

        mRecyclerListAdapter.setOnItemClickListener(new RecyclerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });

        mRecyclerListAdapter.setOnBottomListener(new RecyclerListAdapter.OnBottomListener() {
            @Override
            public void OnBottom() {
                mPresenter.bottomRefresh();
            }
        });

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

    @Override
    public void showRecyclerView(List<DailyJson.Story> stories) {
        mSwipeRefresh.setRefreshing(false);
        mRecyclerListAdapter.clear();
        for (DailyJson.Story story : stories) {
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.setImgLink(story.getImages().get(0));
            recyclerItem.setTitle(story.getTitle());
            mRecyclerListAdapter.addRecyclerItem(recyclerItem);
        }
        mRecyclerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAddRecyclerView(List<DailyJson.Story> stories) {
        mSwipeRefresh.setRefreshing(false);
        for (DailyJson.Story story : stories) {
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.setImgLink(story.getImages().get(0));
            recyclerItem.setTitle(story.getTitle());
            mRecyclerListAdapter.addRecyclerItem(recyclerItem);
        }
        mRecyclerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        mSwipeRefresh.setRefreshing(false);
        Utils.showSnackbar(mRecyclerView, msg);
    }
}
