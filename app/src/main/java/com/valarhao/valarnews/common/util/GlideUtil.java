package com.valarhao.valarnews.common.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideUtil {

    public static void load(Context context, String url, ImageView img) {
        glideload(Glide.with(context), url, img);
    }

    public static void load(Activity activity, String url, ImageView img) {
        glideload(Glide.with(activity), url, img);
    }

    public static void load(FragmentActivity fragmentActivity, String url, ImageView img) {
        glideload(Glide.with(fragmentActivity), url, img);
    }

    public static void load(android.app.Fragment fragment, String url, ImageView img) {
        glideload(Glide.with(fragment), url, img);
    }

    public static void load(Fragment fragment, String url, ImageView img) {
        glideload(Glide.with(fragment), url, img);
    }

    private static void glideload(RequestManager requestManager, String url, ImageView img) {
        requestManager.load(url)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(img);
    }
}
