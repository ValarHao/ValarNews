package com.valarhao.valarnews.module.zhihu.daily;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.zhihu.ZhihuApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class DailyPresenter implements DailyContract.Presenter {

    private static final String TAG = DailyPresenter.class.getSimpleName();

    private final DailyContract.View mDailyView;
    private ZhihuApi mZhihuApi;
    private Calendar mCalendar;

    public DailyPresenter(DailyContract.View mainView) {
        mDailyView = checkNotNull(mainView);
        mDailyView.setPresenter(this);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date()); //设置为当前日期
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "DailyPresenter init!");
        //初始化Retrofit的API接口
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mZhihuApi = retrofit.create(ZhihuApi.class);
        getDailyLatest();
    }

    @Override
    public void swipeRefresh() {
        mCalendar.setTime(new Date());
        getDailyLatest();
    }

    @Override
    public void clickItem(FragmentActivity activity) {

    }

    @Override
    public void bottomRefresh() {
        Date beforeDate = mCalendar.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd"); //日期格式转换
        getDailyBefore(sf.format(beforeDate));
        mCalendar.add(Calendar.DAY_OF_MONTH, -1); //加载过后将日期往前推一天
    }

    /**
     * 加载当天日报
     */
    private void getDailyLatest() {

        mZhihuApi.getDailyLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mDailyView.showError("加载最新日报失败！！！");
                    }

                    @Override
                    public void onNext(DailyJson dailyJson) {
                        mDailyView.showRecyclerView(dailyJson.getStories());
                    }
                });
    }

    /**
     * 加载过往的日报
     * @param date
     */
    private void getDailyBefore(String date) {

        mZhihuApi.getDailyBefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyJson>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mDailyView.showError("加载之前日报失败！！！");
                    }

                    @Override
                    public void onNext(DailyJson dailyJson) {
                        LogUtil.d(TAG, "Daily before date: " + dailyJson.getDate());
                        mDailyView.showAddRecyclerView(dailyJson.getStories());
                    }
                });
    }
}
