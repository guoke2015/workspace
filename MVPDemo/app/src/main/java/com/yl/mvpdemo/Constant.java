package com.yl.mvpdemo;

/**
 * 常量
 * Created by yangle on 2017/6/26.
 */

public class Constant {
    /**
     * 请求数据返回成功
     */
    public static final int NET_CODE_SUCCESS = 0;

    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";

    /**
     * 服务器地址
     */
    public static final String SERVER_URL = "http://japi.juhe.cn/joke/content/";
    /**
     * 聚合数据笑话大全 APPKEY
     */
    public static final String APPKEY="e268f1cf2b90663172206ba9c3a8cc40";
    /**
     * 指定时间之前发布的
     */
    public static final String DESC="desc";
    /**
     * 指定时间之后发布的
     */
    public static final String ASC="asc";

    /**
     * http缓存文件名
     */
    public static final String CACHE_FILE_NAME="HttpCache";
    /**
     * http缓存文件最大大小(50M)
     */
    public static final long CACHE_FILE_MAXSIZE=1024 * 1024 * 50;
    /**
     * 网络可用时缓存最大时间（1分钟）
     */
    public static final int NETWORK_IS_AVAILABLE_CACHE_MAXAGE=1*60;
    /**
     * 网络不可用时缓存最大时间（1天）
     */
    public static final int NETWORK_NOT_AVAILABLE_CACHE_MAXAGE=1*24*60*60;
    /**
     * 连接超时时间（10s）
     */
    public static final int CONNECT_TIME_OUT=10;
    /**
     * 读取数据超时时间（10s）
     */
    public static final int READ_TIME_OUT=10;
    /**
     * 写数据超时时间（10s）
     */
    public static final int WRITE_TIME_OUT=10;
}
