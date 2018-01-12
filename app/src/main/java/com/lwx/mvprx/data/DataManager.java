package com.lwx.mvprx.data;

import com.lwx.mvprx.ApiService;
import com.lwx.mvprx.Constant;
import com.lwx.mvprx.MyApplication;
import com.lwx.mvprx.base.BaseResult;
import com.lwx.mvprx.data.bean.Joke;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : 数据处理类
 *     version: 1.0
 * </pre>
 */

public class DataManager {
    private static volatile DataManager mDataManager;
    private ApiService mApiService;

    public static DataManager getInstance() {
        if (mDataManager == null) {
            synchronized (DataManager.class) {
                if (mDataManager == null) {
                    mDataManager = new DataManager();
                }
            }
        }
        return mDataManager;
    }

    private DataManager() {
        mApiService = MyApplication.getInstance().getRetrofitHelper().getApiService();
    }

    /**
     * 获取笑话大全信息
     *
     * @param page     当前页数,默认1
     * @param pagesize 每次返回条数,默认1,最大20
     * @param sort     类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time     时间戳（10位），如：1418816972
     * @return Joke
     */
    public Flowable<BaseResult<Joke>> getJokes(int page, int pagesize, String sort, String time) {
        return mApiService.getJokes(Constant.APPKEY, page, pagesize, sort, time);
    }
}
