package com.valarhao.valarnews.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.valarhao.valarnews.common.app.App;

import java.util.Map;

public class SharedPreferencesUtil {

    private static final String FILE_NAME = "share_data";

    private static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存数据
     * @param key 键
     * @param object 值
     */
    public static void put(String key, Object object) {
        SharedPreferences.Editor editor = getAppSp().edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (int) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 读取数据
     * @param key 键
     * @param defaultObject 默认的值
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return getAppSp().getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return getAppSp().getInt(key, (int) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return getAppSp().getBoolean(key, (boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return getAppSp().getFloat(key, (float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return getAppSp().getLong(key, (long) defaultObject);
        }
        return null;
    }

    /**
     * 移除键所对应的值
     * @param key 键
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getAppSp().edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences.Editor editor = getAppSp().edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个键是否已经存在
     * @param key 键
     * @return
     */
    public static boolean contains(String key) {
        return getAppSp().contains(key);
    }

    /**
     * 获取所有键值对
     * @return
     */
    public static Map<String, ?> getAll() {
        return getAppSp().getAll();
    }
}
