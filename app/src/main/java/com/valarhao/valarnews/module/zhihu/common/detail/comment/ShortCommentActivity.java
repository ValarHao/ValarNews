package com.valarhao.valarnews.module.zhihu.common.detail.comment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ShortCommentActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "EXTRA_ID";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_activity_detail_comment);

        mToolbar = (Toolbar) findViewById(R.id.toolbarZhihuComment);

        // ToolBar
        String str = getResources().getString(R.string.comment_title);
        mToolbar.setTitle(str);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rcvZhihuComment);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(recyclerLayoutManager);
        mRecyclerAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        getDetailShortComment(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载日报短评论
     * @param id
     */
    private void getDetailShortComment(int id) {

        RetrofitHelper.sZhihuApi.getDetailShortInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<DetailShortJson, Observable<DetailShortJson.Comment>>() {
                    @Override
                    public Observable<DetailShortJson.Comment> call(DetailShortJson detailShortJson) {
                        return Observable.from(detailShortJson.getComments());
                    }
                })
                .subscribe(new Observer<DetailShortJson.Comment>() {

                    @Override
                    public void onCompleted() {
                        mRecyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.showToast(ShortCommentActivity.this, R.string.network_error_message);
                    }

                    @Override
                    public void onNext(DetailShortJson.Comment comment) {
                        mRecyclerAdapter.addRecyclerItem(comment);
                    }
                });
    }

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, ShortCommentActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
