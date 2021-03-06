package com.lwx.mvprx.feature.JokeInfo;

import android.content.res.AssetManager;

import com.lwx.mvprx.Constant;
import com.lwx.mvprx.MyApplication;
import com.lwx.mvprx.base.BaseObserver;
import com.lwx.mvprx.base.BasePresenter;
import com.lwx.mvprx.base.BaseResult;
import com.lwx.mvprx.data.DataManager;
import com.lwx.mvprx.data.bean.Joke;
import com.lwx.mvprx.data.local.Girls;
import com.lwx.mvprx.data.local.GirlsUrls;
import com.lwx.mvprx.data.remote.RxSchedulers;
import com.lwx.mvprx.test.test;
import com.lwx.mvprx.util.FileUtils;
import com.lwx.mvprx.util.SPUtils;
import com.lwx.mvprx.util.ToastUtils;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : 笑话大全表示层
 *     version: 1.0
 * </pre>
 */

public class JokePresenter<V extends JokeView> extends BasePresenter<V> {
    private DataManager mDataManager;

    public JokePresenter() {
        super();
        mDataManager = DataManager.getInstance();
    }

    /**
     * 获取笑话大全信息
     *
     * @param page     当前页数,默认1
     * @param pagesize 每次返回条数,默认1,最大20
     * @param sort     类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time     时间戳（10位），如：1418816972
     */
    public void getJokes(int page, int pagesize, String sort, String time) {
        if (mViewRef.get() != null) {
            mDataManager.getJokes(page, pagesize, sort, time)
                    ///未封装时Rxjava2使用
                    /*.subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            mViewRef.get().showLoading();
                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate(new Action() {
                        @Override
                        public void run() throws Exception {
                            mViewRef.get().hideLoading();
                        }
                    })
                    .compose(mViewRef.get().bindLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<Joke>() {
                        @Override
                        public void resultSuccess(Joke joke) {
                            mViewRef.get().showSuccess(joke);
                        }

                        @Override
                        public void resultFail(String err) {
                            mViewRef.get().showFail(err);
                        }
                    })*/
                    .compose(RxSchedulers.<BaseResult<Joke>>flow_io_main(mViewRef.get()))
                    .subscribeWith(new BaseObserver<Joke>() {
                        @Override
                        public void resultSuccess(Joke joke) {
                            mViewRef.get().showSuccess(joke);
                        }

                        @Override
                        public void resultFail(String err) {
                            mViewRef.get().showFail(err);
                        }
                    });
        }
    }

    /**
     * 是否是第一次启动：将数据写入数据库中
     */
    public void isFirstInit() {
        final SPUtils utils = new SPUtils(MyApplication.getInstance().getApplicationContext(), Constant.SP_DATA);
        if (utils.getBoolean(Constant.FIRST, true)) {

            ///将外部数据库导入（导入成功，但查询时未查询到数据,原因暂时未知）
            //数据库路径
            StringBuilder sb = new StringBuilder();
            sb.append("/data/data/");
            sb.append(MyApplication.getInstance().getApplicationContext().getPackageName());
            sb.append("/databases/girls-db");
            AssetManager assetManager = MyApplication.getInstance().getApplicationContext().getAssets();
            try {
                RxSchedulers.observable(FileUtils.writeFileFromIS(sb.toString(),
                        assetManager.open("girls-db"), false))
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    utils.putBoolean(Constant.FIRST, false);
                                    ToastUtils.success(MyApplication.getInstance().getApplicationContext(), "插入数据成功");
                                } else {
                                    utils.putBoolean(Constant.FIRST, true);
                                    ToastUtils.success(MyApplication.getInstance().getApplicationContext(), "插入数据失败");
                                }
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*Observable.create(new ObservableOnSubscribe<List<Girls>>() {

                @Override
                public void subscribe(ObservableEmitter<List<Girls>> e) throws Exception {
                    e.onNext(test.getGirls());
                    e.onComplete();
                }
            }).subscribeOn(Schedulers.io())
                    .map(new Function<List<Girls>, Long>() {
                        @Override
                        public Long apply(List<Girls> girls) throws Exception {
                            if (mViewRef.get() != null) {
                                return mDataManager.insertToArray(girls);
                            }
                            return null;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (aLong > 0) {
                                ToastUtils.success(MyApplication.getInstance().getApplicationContext(), "插入数据成功");
                            } else {
                                utils.putBoolean(Constant.FIRST, true);
                                ToastUtils.success(MyApplication.getInstance().getApplicationContext(), "插入数据失败");
                            }
                        }
                    });
            Observable.create(new ObservableOnSubscribe<List<GirlsUrls>>() {
                @Override
                public void subscribe(ObservableEmitter<List<GirlsUrls>> e) throws Exception {
                    e.onNext(test.getGirlsUrls());
                    e.onComplete();
                }
            }).subscribeOn(Schedulers.io())
                    .map(new Function<List<GirlsUrls>, Long>() {
                        @Override
                        public Long apply(List<GirlsUrls> girlsUrls) throws Exception {
                            if (mViewRef.get() != null) {
                                return mDataManager.insertToArray(girlsUrls);
                            }
                            return null;
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            if (aLong > 0) {
                                utils.putBoolean(Constant.FIRST, false);
                                ToastUtils.success(MyApplication.getInstance().getApplicationContext(), "插入数据成功");
                            } else {
                                utils.putBoolean(Constant.FIRST, true);
                                ToastUtils.success(MyApplication.getInstance().getApplicationContext(), "插入数据失败");
                            }
                        }
                    });*/
        }
    }
}
