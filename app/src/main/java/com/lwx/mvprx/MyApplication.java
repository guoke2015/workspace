package com.lwx.mvprx;

import android.app.Application;

import com.lwx.mvprx.data.local.GreenDaoManager;
import com.lwx.mvprx.data.remote.RetrofitHelper;
import com.squareup.leakcanary.LeakCanary;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : MyApplication
 *     version: 1.0
 * </pre>
 */

public class MyApplication extends Application {
    private static MyApplication myApplication = null;
    private RetrofitHelper mRetrofitHelper;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mRetrofitHelper = RetrofitHelper.getInstance();
        GreenDaoManager.getInstance();
    }

    /**
     * 获取Retrofit帮助类
     *
     * @return RetrofitHelper
     */
    public RetrofitHelper getRetrofitHelper() {
        return mRetrofitHelper;
    }
}
