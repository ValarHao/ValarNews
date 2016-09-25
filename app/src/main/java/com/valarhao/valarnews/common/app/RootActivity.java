package com.valarhao.valarnews.common.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.util.Utils;
import com.valarhao.valarnews.module.main.MainActivity;

public class RootActivity extends Activity {

    private static final int DISPLAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_activity);

        Utils.translucentStatusBar(this);

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
    }
}
