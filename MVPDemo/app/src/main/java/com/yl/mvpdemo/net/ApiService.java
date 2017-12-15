package com.yl.mvpdemo.net;

import com.yl.mvpdemo.bean.BaseResult;
import com.yl.mvpdemo.bean.Joke;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 请求参数接口
 * Created by yangle on 2017/6/26.
 */

public interface ApiService {

    /**
     * 获取笑话大全信息
     * @param key 申请的key
     * @param page 当前页数,默认1
     * @param pagesize 每次返回条数,默认1,最大20
     * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time 时间戳（10位），如：1418816972
     * @return Joke
     */
    @GET("list.from")
    Observable<BaseResult<Joke>> getJokeInfo(@Query("key") String key, @Query("page") int page, @Query("pagesize") int pagesize, @Query("sort")String sort, @Query("time") String time);
}
