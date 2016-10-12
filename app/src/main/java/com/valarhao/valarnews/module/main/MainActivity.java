package com.valarhao.valarnews.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.valarhao.valarnews.R;
import com.valarhao.valarnews.common.base.BaseActivity;
import com.valarhao.valarnews.common.base.BaseFragment;
import com.valarhao.valarnews.module.zhihu.section.SectionFragment;
import com.valarhao.valarnews.module.zhihu.daily.DailyFragment;
import com.valarhao.valarnews.module.zhihu.hot.HotFragment;
import com.valarhao.valarnews.module.zhihu.theme.ThemeFragment;

import java.util.ArrayList;
import java.util.List;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class MainActivity extends BaseActivity implements MainContract.View {

    private MainContract.Presenter mPresenter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        new MainPresenter(this);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        showTabZhihu();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showTabZhihu() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new DailyFragment("日报"));
        fragments.add(new ThemeFragment("主题"));
        fragments.add(new SectionFragment("专栏"));
        fragments.add(new HotFragment("热门"));
        mTabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mTabAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
