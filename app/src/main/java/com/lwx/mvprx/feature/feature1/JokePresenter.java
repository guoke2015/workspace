package com.lwx.mvprx.feature.feature1;

import com.lwx.mvprx.base.BaseObserver;
import com.lwx.mvprx.base.BasePresenter;
import com.lwx.mvprx.base.BaseResult;
import com.lwx.mvprx.data.DataManager;
import com.lwx.mvprx.data.bean.Joke;
import com.lwx.mvprx.data.remote.RxSchedulers;

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
                    .compose(RxSchedulers.<BaseResult<Joke>>io_main(mViewRef.get()))
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
}
