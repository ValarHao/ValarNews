package com.valarhao.valarnews.module.zhihu.section;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.base.BaseFragment;
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.zhihu.RecyclerItem;

import java.util.List;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

@SuppressLint("ValidFragment")
public class SectionFragment extends BaseFragment implements SectionContract.View {

    private SectionContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private SwipeRefreshLayout mSwipeRefresh;

    public SectionFragment(String title) {
        super(title);
    }

    @Override
    public void setPresenter(SectionContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zhihu_fragment_common, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new SectionPresenter(this);

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.zhihuRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        mRecyclerAdapter = new RecyclerAdapter(this.getContext());
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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

        mPresenter.init();
    }

    @Override
    public void showRecyclerView(List<SectionJson.Section> sections) {
        mSwipeRefresh.setRefreshing(false);
        mRecyclerAdapter.clear();
        for (SectionJson.Section section : sections) {
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.setImgLink(section.getThumbnail());
            recyclerItem.setTitle(section.getName());
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
