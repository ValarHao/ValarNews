package com.valarhao.valarnews.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.valarhao.valarnews.common.app.App;

public class Utils {

    /**
     * 获取versionCode
     * @return
     */
    public static int getVersionCode() {
        int code = 0;
        try {
            PackageInfo info = App.getInstance().getPackageManager().getPackageInfo(App.getInstance().getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {}
        return code;
    }

    /**
     * 获取versionName
     * @return
     */
    public static String getVersionName()
    {
        try {
            PackageManager packageManager = App.getInstance().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(App.getInstance().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查是否为null并返回
     * @param reference 需要检查的对象
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * 短时间显示Toast
     * @param context
     */
    /*public static void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }*/

    public static void showToast(Context context, @StringRes int resId) {
        Toast.makeText(context, context.getResources().getText(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Snackbar
     * @param view
     */
    /*public static void showSnackbar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }*/

    public static void showSnackbar(View view, @StringRes int resId) {
        Snackbar.make(view, App.getInstance().getResources().getText(resId), Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 刷新SwipeRefreshLayout
     * @param swipeRefresh
     */
    public static void showSwipeRefresh(final SwipeRefreshLayout swipeRefresh) {
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
    }

    /**
     * 当前网络是否可用
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable() && networkInfo.isConnectedOrConnecting();
        }
        return false;
    }

    /**
     * 当前是否为WIFI网络
     * @return
     */
    public static boolean isNetworkWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * 当前是否为移动网络
     * @return
     */
    public static boolean isNetworkMobile() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    /**
     * 给activity设置透明度
     * @param activity
     * @param alpha 0.0-1.0
     */
    public static void activityAlpha(Activity activity, float alpha) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = alpha;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * dp转px
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().density);
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
