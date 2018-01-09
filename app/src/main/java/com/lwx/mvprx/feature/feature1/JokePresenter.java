package com.lwx.mvprx.feature.feature1;

import com.lwx.mvprx.base.BaseObserver;
import com.lwx.mvprx.base.BasePresenter;
import com.lwx.mvprx.data.DataManager;
import com.lwx.mvprx.data.bean.Joke;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : liwx
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

    public void getJokes(int page, int pagesize, String sort, String time) {
        if (mViewRef.get() != null) {
            mDataManager.getJokes(page, pagesize, sort, time)
                    .subscribeOn(Schedulers.io())
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
                    .subscribe(new BaseObserver<Joke>() {
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
}
