package com.yl.mvpdemo;

import android.app.Application;

import com.yl.mvpdemo.net.RetrofitHelper;

/**
 * @author liwx
 * @date 2017/12/8 11:13
 */

public class MyApplication extends Application {
    private static MyApplication ourInstance = null;
    private static RetrofitHelper apiHelper;

    public static MyApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        apiHelper = RetrofitHelper.getInstance();
    }

    public RetrofitHelper getApiHelper(){
        return apiHelper;
    }
}
