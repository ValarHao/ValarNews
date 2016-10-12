package com.valarhao.valarnews.module.zhihu.daily;

import android.support.v4.app.FragmentActivity;

import com.valarhao.valarnews.common.util.LogUtil;
import com.valarhao.valarnews.module.main.RetrofitHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.valarhao.valarnews.common.util.Utils.checkNotNull;

public class DailyPresenter implements DailyContract.Presenter {

    private static final String TAG = DailyPresenter.class.getSimpleName();

    private final DailyContract.View mDailyView;
    private Calendar mCalendar;

    public DailyPresenter(DailyContract.View dailyView) {
        mDailyView = checkNotNull(dailyView);
        mDailyView.setPresenter(this);
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date()); //设置为当前日期
    }

    @Override
    public void init() {
        LogUtil.d(TAG, "DailyPresenter init!");
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

        RetrofitHelper.sZhihuApi.getDailyLatest()
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
                        mDailyView.showRecyclerView(dailyJson.getDailies());
                    }
                });
    }

    /**
     * 加载过往的日报
     * @param date
     */
    private void getDailyBefore(String date) {

        RetrofitHelper.sZhihuApi.getDailyBefore(date)
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
                        mDailyView.showAddRecyclerView(dailyJson.getDailies());
                    }
                });
    }
}
