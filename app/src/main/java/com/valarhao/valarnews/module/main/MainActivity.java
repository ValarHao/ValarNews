package com.valarhao.valarnews.module.main;

import android.os.Bundle;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }
}
