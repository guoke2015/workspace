package com.lwx.mvprx.feature.GirlsInfo;

import com.lwx.mvprx.base.BasePresenter;
import com.lwx.mvprx.data.DataManager;
import com.lwx.mvprx.data.local.Girls;
import com.lwx.mvprx.data.local.GirlsDao;
import com.lwx.mvprx.data.remote.RxSchedulers;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class GirlsPresenter<V extends GirlsView> extends BasePresenter<V> {
    private DataManager mDataManager;

    public GirlsPresenter() {
        super();
        mDataManager = DataManager.getInstance();
    }

    /**
     * 根据时间查询Girls信息
     *
     * @param date
     */
    public void queryGirlsFromDate(final Date date) {
        Observable.create(new ObservableOnSubscribe<List<Girls>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Girls>> e) throws Exception {
                e.onNext(mDataManager.queryFromWhere(new Girls(), GirlsDao.Properties.Date.eq(date)));
                e.onComplete();
            }
        })
                .compose(RxSchedulers.<List<Girls>>observable_io_main(mViewRef.get()))
                .subscribe(new Consumer<List<Girls>>() {
                    @Override
                    public void accept(List<Girls> girls) throws Exception {
                        if (mViewRef.get() != null) {
                            mViewRef.get().showSuccess(girls);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mViewRef.get() != null) {
                            mViewRef.get().showFail(throwable.getMessage());
                        }
                    }
                });
    }
}
