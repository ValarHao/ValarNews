package com.valarhao.valarnews.common.app;

public class Constant {

    public static final int NETWORK_TIMEOUT = 10;

    //缓存文件路径
    public static final String CACHE_PATH = App.getInstance().getCacheDir().getAbsolutePath();
    //缓存文件大小
    public static final long CACHE_PATH_SIZE = 1024 * 1024 * 10;
    //有网络时，不缓存，最大保存时长为0
    public static final int CACHE_NETWORK_CONNECTED_SAVE_TIME = 0;
    //无网络时，设置超时为4周
    public static final int CACHE_NETWORK_UNCONNECTED_SAVE_TIME = 60 * 60 * 24 * 28;
}
