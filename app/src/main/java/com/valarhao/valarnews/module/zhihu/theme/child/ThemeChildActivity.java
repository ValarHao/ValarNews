package com.valarhao.valarnews.module.zhihu.theme.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.valarhao.valarnews.R;

public class ThemeChildActivity extends AppCompatActivity {

    private static final String EXTRA_ID = "EXTRA_ID";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_detail_comment_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbarZhihuComment);

        // ToolBar
        mToolbar.setTitle("评论");
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
    }

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, ThemeChildActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
