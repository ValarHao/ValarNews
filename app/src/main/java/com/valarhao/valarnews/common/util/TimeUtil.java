package com.valarhao.valarnews.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳转换工具
 */

public class TimeUtil {

    public static String format(long time) {
        String str = "";
        long interval = System.currentTimeMillis()/1000 - time;
        if ( interval < 60 ) { //1分钟内
            str = "刚刚";
        } else if ( interval >= 60 && interval < 3600) { //1小时内
            str = interval/60 + "分钟前";
        } else if ( interval >= 3600 && interval < 86400 ) { //24小时内
            str = interval/3600 + "小时前";
        } else { //24小时前
            Date date = new Date( time * 1000 );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = sdf.format(date);
        }
        return str;
    }
}
