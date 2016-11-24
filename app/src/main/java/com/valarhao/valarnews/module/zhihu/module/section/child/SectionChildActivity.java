package com.valarhao.valarnews.module.zhihu.module.section.child;

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
import android.view.View;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.app.App;
import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.main.RetrofitHelper;
import com.valarhao.valarnews.module.zhihu.common.RecyclerItem;
import com.valarhao.valarnews.module.zhihu.common.RecyclerListAdapter;
import com.valarhao.valarnews.module.zhihu.common.detail.DetailActivity;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SectionChildActivity extends AppCompatActivity {

    private static final String TAG = SectionChildActivity.class.getSimpleName();

    private static final String EXTRA_ID = "EXTRA_ID";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerListAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_activity_child);

        mToolbar = (Toolbar) findViewById(R.id.toolbarZhihuChild);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcvZhihuChild);

        // ToolBar
        String str = getResources().getString(R.string.section_detail);
        mToolbar.setTitle(str);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // RecyclerView
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(recyclerLayoutManager);
        mRecyclerAdapter = new RecyclerListAdapter(this);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setOnItemClickListener(new RecyclerListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RecyclerItem recyclerItem = mRecyclerAdapter.getRecyclerItem(position);
                Intent intent = DetailActivity.newIndexIntent(SectionChildActivity.this, recyclerItem.getId());
                startActivity(intent);
            }
        });

        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        LogUtil.d(TAG, "id: " + id);
        getSectionChild(id);
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
     * 加载专栏详情
     * @param id
     */
    private void getSectionChild(int id) {

        RetrofitHelper.sZhihuApi.getSectionChild(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<SectionChildJson, Observable<SectionChildJson.Story>>() {
                    @Override
                    public Observable<SectionChildJson.Story> call(SectionChildJson sectionChildJson) {
                        return Observable.from(sectionChildJson.getStories());
                    }
                })
                .subscribe(new Observer<SectionChildJson.Story>() {

                    @Override
                    public void onCompleted() {
                        mRecyclerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        String str = App.getInstance().getResources().getString(R.string.network_error_message);
                        Utils.showSnackbar(mRecyclerView, str);
                    }

                    @Override
                    public void onNext(SectionChildJson.Story story) {
                        mRecyclerAdapter.addRecyclerItem(story);
                    }
                });
    }

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, SectionChildActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
