package com.lwx.mvprx.data.local;

import com.lwx.mvprx.MyApplication;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by Ljl on 2018/1/14.
 */

public class GreenDaoManager<T> {
    private final static String DB_NAME = "girls-db";
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    //单例
    private static volatile GreenDaoManager mInstance;

    private GreenDaoManager() {
        MyOpenHelper myOpenHelper = new
                MyOpenHelper(MyApplication.getInstance().getApplicationContext(), DB_NAME, null);
        mDaoMaster = new DaoMaster(myOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 向数据库中插入或更新数据(数组)
     *
     * @param list 要插入的数据集合(对象)
     * @return 插入或更新成功数据个数
     */
    public Long insertToArray(List<T> list) {
        if (list == null || list.isEmpty() || (getDao(list.get(0).getClass()) == null)) {
            return new Long((long) -1);
        } else {
            getDao(list.get(0).getClass()).insertOrReplaceInTx(list);
            return new Long(1);
        }
    }

    /**
     * 向数据库中插入或更新数据(单条数据)
     *
     * @param t 对象
     * @return
     */
    public Long insertOrReplace(T t) {
        if (getDao(t.getClass()) == null) {
            return new Long((long) -1);
        } else {
            return getDao(t.getClass()).insertOrReplace(t);
        }
    }

    /**
     * 根据where条件查询数据
     *
     * @param t     对象
     * @param where where条件，如：GirlsDao.Properties.Date.eq("date")
     * @return list列表
     */
    public List<T> queryFromWhere(T t, WhereCondition where) {
        if (getDao(t.getClass()) == null) {
            return null;
        } else {
            Query<T> query = getDao(t.getClass()).queryBuilder()
                    .where(where)
                    .build();
            return query.list();
        }
    }

    /**
     * 查询所有数据
     *
     * @param t
     * @return
     */
    public List<T> queryAllData(T t) {
        if (getDao(t.getClass()) == null) {
            return null;
        } else {
            Query<T> query = getDao(t.getClass()).queryBuilder().build();
            return query.list();
        }
    }

    /**
     * 获取Dao
     *
     * @param className 数据库表名：如Girls.class
     * @return dao
     */
    private AbstractDao getDao(Object className) {
        if (Girls.class.equals(className)) {
            return mDaoSession.getDao(Girls.class);
        } else if (GirlsUrls.class.equals(className)) {
            return mDaoSession.getDao(GirlsUrls.class);
        }
        return null;
    }
}
