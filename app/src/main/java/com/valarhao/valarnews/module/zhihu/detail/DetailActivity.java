package com.valarhao.valarnews.module.zhihu.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.GlideUtil;
import com.valarhao.valarnews.common.util.HtmlUtil;

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

    private boolean isImageShow = false;

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
                if ( scrollY - oldScrollY > 0 ) { //下移

                } else if ( scrollY - oldScrollY < 0 ) { //上移

                }
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

    public static Intent newIndexIntent(Context context, int id) {
        Intent newIntent = new Intent(context, DetailActivity.class);
        newIntent.putExtra(EXTRA_ID, id);
        return newIntent;
    }
}
