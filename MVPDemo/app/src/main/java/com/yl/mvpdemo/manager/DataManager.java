package com.yl.mvpdemo.manager;

import com.yl.mvpdemo.Constant;
import com.yl.mvpdemo.MyApplication;
import com.yl.mvpdemo.bean.BaseResult;
import com.yl.mvpdemo.bean.Joke;
import com.yl.mvpdemo.net.ApiService;

import java.util.List;

import io.reactivex.Observable;

/**
 * 数据处理
 * Created by yangle on 2017/6/27.
 */

public class DataManager {

    private static DataManager dataManager;
    private ApiService apiService;

    public static DataManager getInstance() {
        return dataManager == null ? dataManager = new DataManager() : dataManager;
    }

    private DataManager() {
        apiService = MyApplication.getInstance().getApiHelper().getRetrofitService();
    }

    /**
     * 笑话大全
     * @param page 当前页数,默认1
     * @param pagesize 每次返回条数,默认1,最大20
     * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time 时间戳（10位），如：1418816972
     * @return
     */
    public Observable<BaseResult<List<Joke>>> getJokeInfo(int page, int pagesize, String sort, String time) {
        return apiService.getJokeInfo(Constant.APPKEY, page,pagesize,sort,time);
    }
}
