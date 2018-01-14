package com.lwx.mvprx.data;

import com.lwx.mvprx.MyApplication;
import com.lwx.mvprx.data.local.DaoMaster;
import com.lwx.mvprx.data.local.DaoSession;

/**
 * Created by Ljl on 2018/1/14.
 */

public class GreenDaoManager {
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile GreenDaoManager mInstance; //单例

    private GreenDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new
                DaoMaster.DevOpenHelper(MyApplication.getInstance().getApplicationContext(), "girls-db", null);//此处为自己需要处理的表
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {//保证异步处理安全操作
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
