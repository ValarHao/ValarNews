package com.valarhao.valarnews.common.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();

    private static Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    public CrashHandler(Context context) {
        mContext = context;
    }

    public static void init(CrashHandler crashHandler) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.toString());
        Log.e(TAG, e.toString());
        Log.e(TAG, collectCrashDeviceInfo());
        Log.e(TAG, getCrashInfo(e));
        // 调用系统错误机制
        mDefaultCrashHandler.uncaughtException(t, e);
        Toast.makeText(mContext, "抱歉,程序发生异常即将退出", Toast.LENGTH_SHORT).show();
        App.getInstance().exit();
    }

    /**
     * 得到程序崩溃的详细信息
     */
    private String getCrashInfo(Throwable e) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.setStackTrace(e.getStackTrace());
        e.printStackTrace(printWriter);
        return result.toString();
    }

    /**
     * 收集程序崩溃的设备信息
     */
    private String collectCrashDeviceInfo() {
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            String versionName = pi.versionName;
            String model = android.os.Build.MODEL;
            String androidVersion = android.os.Build.VERSION.RELEASE;
            String manufacturer = android.os.Build.MANUFACTURER;
            return versionName + "  " + model + "  " + androidVersion + "  " + manufacturer;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
