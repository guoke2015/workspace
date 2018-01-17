package com.lwx.mvprx.data;

import com.lwx.mvprx.ApiService;
import com.lwx.mvprx.Constant;
import com.lwx.mvprx.MyApplication;
import com.lwx.mvprx.base.BaseResult;
import com.lwx.mvprx.data.bean.Joke;
import com.lwx.mvprx.data.local.GreenDaoManager;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

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

public class DataManager<T> {
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


    /**
     * 向数据库中插入或更新数据(数组)
     *
     * @param list 要插入的数据集合(对象)
     * @return 插入或更新成功数据个数
     */
    public Long insertToArray(final List<T> list) {
        return GreenDaoManager.getInstance().insertToArray(list);
    }

    /**
     * 向数据库中插入或更新数据(单条数据)
     *
     * @param t 对象
     * @return
     */
    public long insertOrReplace(T t) {
        return GreenDaoManager.getInstance().insertOrReplace(t);
    }

    /**
     * 根据where条件查询数据
     *
     * @param t     对象
     * @param where where条件，如：GirlsDao.Properties.Date.eq("date")
     * @return list列表
     */
    public List<T> queryFromWhere(T t, WhereCondition where) {
        return GreenDaoManager.getInstance().queryFromWhere(t, where);
    }

    /**
     * 查询所有数据
     *
     * @param t
     * @return
     */
    public List<T> queryAllData(T t) {
        return GreenDaoManager.getInstance().queryAllData(t);
    }
}
