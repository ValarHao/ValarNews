package com.valarhao.valarnews.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.valarhao.valarnews.common.base.BaseFragment;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;

    public TabAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<BaseFragment> fragments) {
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
