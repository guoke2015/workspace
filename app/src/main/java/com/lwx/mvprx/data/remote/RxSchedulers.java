package com.lwx.mvprx.data.remote;

import com.lwx.mvprx.base.IBaseView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RxSchedulers {
    /**
     * 基本请求
     */
    public static <T> FlowableTransformer<T, T> io_main(final IBaseView baseView) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                baseView.showLoading();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                baseView.hideLoading();
                            }
                        })
                        .compose(baseView.bindLifecycle())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
