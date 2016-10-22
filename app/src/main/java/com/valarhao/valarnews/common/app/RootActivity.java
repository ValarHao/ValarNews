package com.valarhao.valarnews.common.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.main.MainActivity;

public class RootActivity extends Activity {

    private static final int DISPLAY_TIME = 2000;

    private ViewGroup cacheViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(RootActivity.this, MainActivity.class);
                RootActivity.this.startActivity(intent);
                RootActivity.this.finish();
                RootActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, DISPLAY_TIME);

        cacheViewGroup = (ViewGroup) findViewById(R.id.cacheViewGroup);

        //缓存layout使首次进入不卡顿
        new Thread(new Runnable() {
            @Override
            public void run() {
                cacheResoures();
            }
        }).start();
    }

    synchronized private void cacheResoures() {
        try {
            View v = LayoutInflater.from(RootActivity.this).inflate(R.layout.zhihu_activity_detail, cacheViewGroup, false);
            v.setVisibility(View.VISIBLE);
        } catch (Throwable e) { }
    }
}
