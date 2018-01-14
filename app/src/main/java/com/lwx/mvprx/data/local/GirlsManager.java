package com.lwx.mvprx.data.local;

import com.lwx.mvprx.data.GreenDaoManager;

import org.greenrobot.greendao.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Ljl on 2018/1/14.
 */

public class GirlsManager {
    private GirlsDao girlsDao;
    private static volatile GirlsManager girlsManager;

    public static GirlsManager getInstance() {
        if (girlsManager == null) {
            synchronized (GirlsManager.class) {
                if (girlsManager == null) {
                    girlsManager = new GirlsManager();
                }
            }
        }
        return girlsManager;
    }

    private GirlsManager() {
        girlsDao = GreenDaoManager.getInstance().getSession().getGirlsDao();
    }

    public void insertOrReplace(final List<Girls> girls) {
        if (girls == null || girls.isEmpty()) {
            return;
        }
        girlsDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (Girls girl : girls) {
                    girlsDao.insertOrReplace(girl);
                }
            }
        });
    }

    public void update(Girls girls) {
        girlsDao.update(girls);
    }

    public List<Girls> queryFromDate(Date date) {
        Query<Girls> girlsQuery = girlsDao.queryBuilder()
                .where(GirlsDao.Properties.Date.eq(date))
                .build();
        List<Girls> girls = girlsQuery.list();
        return girls;
    }

    public void delete(Girls girls) {
        girlsDao.delete(girls);
    }
}
