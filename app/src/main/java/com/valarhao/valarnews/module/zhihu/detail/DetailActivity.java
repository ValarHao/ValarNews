package com.valarhao.valarnews.module.zhihu.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.GlideUtil;
import com.valarhao.valarnews.common.util.HtmlUtil;
import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.common.util.Utils;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    private static final String EXTRA_ID = "EXTRA_ID";

    private DetailContract.Presenter mPresenter;
    private CollapsingToolbarLayout mClpToolbar;
    private ImageView mImgBar;
    private TextView mTxtBar;
    private Toolbar mToolbar;
    private NestedScrollView mScroller;
    private WebView mWeb;
    private LinearLayout mBottom;
    private TextView mBottomLike;
    private TextView mBottomComment;

    private boolean isImageShow = false;
    private boolean isBottomShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_detail_activity);
        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        new DetailPresenter(this, id);

        mClpToolbar = (CollapsingToolbarLayout) findViewById(R.id.clpZhihuBar);
        mImgBar = (ImageView) findViewById(R.id.imgZhihuBar);
        mTxtBar = (TextView) findViewById(R.id.txtZhihuBar);
        mToolbar = (Toolbar) findViewById(R.id.toolbarZhihu);
        mScroller = (NestedScrollView) findViewById(R.id.scrollerZhihu);
        mWeb = (WebView) findViewById(R.id.webZhihu);
        mBottom = (LinearLayout) findViewById(R.id.llZhihuBottom);
        mBottomLike = (TextView) findViewById(R.id.txtZhihuBottomLike);
        mBottomComment = (TextView) findViewById(R.id.txtZhihuBottomComment);

        // ToolBar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // X5WebView
        WebSettings webSettings = mWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        mWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }
        });

        // NestedScrollView
        mScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if ( scrollY - oldScrollY > 0 && isBottomShow ) { //下移
                    LogUtil.d("test", "Scroll down!");
                    isBottomShow = false;
                    mBottom.animate().translationY(mBottom.getHeight());
                } else if ( scrollY - oldScrollY < 0 && !isBottomShow ) { //上移
                    LogUtil.d("test", "Scroll up!");
                    isBottomShow = true;
                    mBottom.animate().translationY(0);
                }
            }
        });

        //mBottomComment
        mBottomComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clickComment(DetailActivity.this);
            }
        });

        mPresenter.init();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDetailInfo(DetailInfoJson detailInfoJson) {
        if (!isImageShow) {
            GlideUtil.load(this, detailInfoJson.getImage(), mImgBar);
        }
        mClpToolbar.setTitle(detailInfoJson.getTitle());
        mTxtBar.setText(detailInfoJson.getImageSource());
        String htmlData = HtmlUtil.createHtmlData(detailInfoJson.getBody(), detailInfoJson.getCss(), detailInfoJson.getJs());
        mWeb.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    @Override
    public void showDetailExtra(DetailExtraJson detailExtraJson) {
        mBottomLike.setText(detailExtraJson.getPopularity() + "");
        mBottomComment.setText(detailExtraJson.getShortComments() + "");
    }

    @Override
    public void showError(String msg) {
        Utils.showToast(this, msg);
    }

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, DetailActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
