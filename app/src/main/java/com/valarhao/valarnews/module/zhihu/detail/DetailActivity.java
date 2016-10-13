package com.valarhao.valarnews.module.zhihu.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.valarhao.valarnews.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_detail);
        int id = getIntent().getIntExtra(EXTRA_ID, 0);
        new DetailPresenter(this, id);

        mClpToolbar = (CollapsingToolbarLayout) findViewById(R.id.clpZhihuBar);
        mImgBar = (ImageView) findViewById(R.id.imgZhihuBar);
        mTxtBar = (TextView) findViewById(R.id.txtZhihuBar);
        mToolbar = (Toolbar) findViewById(R.id.toolbarZhihu);
        mScroller = (NestedScrollView) findViewById(R.id.scrollerZhihu);
        mWeb = (WebView) findViewById(R.id.webZhihu);

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

        mScroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, DetailActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
