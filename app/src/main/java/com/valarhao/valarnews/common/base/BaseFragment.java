package com.valarhao.valarnews.common.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valarhao.valarnews.R;

@SuppressLint("ValidFragment")
public class BaseFragment extends Fragment {

    private String mTitle;

    public BaseFragment(String title) {
        mTitle = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zhihu_daily_fragment, container, false);
    }

    public String getTitle() {
        return mTitle;
    }
}
