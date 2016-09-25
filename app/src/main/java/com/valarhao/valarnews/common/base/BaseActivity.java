package com.valarhao.valarnews.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.valarhao.valarnews.common.app.App;

public class BaseActivity extends AppCompatActivity {

    private static final int BACK_PRESSED_INTERVAL = 2000;
    private long mLastBackPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        if ( System.currentTimeMillis() - mLastBackPressedTime > BACK_PRESSED_INTERVAL ) {
            mLastBackPressedTime = System.currentTimeMillis();
        } else {
            App.getInstance().exit();
        }
    }
}
