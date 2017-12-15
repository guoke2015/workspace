package com.yl.mvpdemo.bean;

import com.yl.mvpdemo.Constant;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DefaultObserver;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/11
 *     desc   : 返回结果统一处理
 *     version: 1.0
 * </pre>
 */
public abstract class BaseObserver<T> extends DefaultObserver<BaseResult<T>> {

    @Override
    public void onNext(BaseResult<T> tBaseResult) {
        //获取数据成功
        if (tBaseResult.getError_code() == Constant.NET_CODE_SUCCESS) {
            resultSuccess(tBaseResult.getResult());
        } else {
            resultFail(tBaseResult.getReason());
        }
    }

    @Override
    public void onError(Throwable e) {
        //处理常见的几种连接错误
        if (e instanceof SocketTimeoutException) {
            resultFail(Constant.SOCKET_TIMEOUT_EXCEPTION);
        } else if (e instanceof ConnectException) {
            resultFail(Constant.CONNECT_EXCEPTION);
        } else if (e instanceof UnknownHostException) {
            resultFail(Constant.UNKNOWN_HOST_EXCEPTION);
        } else {
            resultFail(e.getMessage());
        }

    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功返回结果
     *
     * @param data
     */
    public abstract void resultSuccess(T data);

    /**
     * 请求失败返回结果
     *
     * @param err
     */
    public abstract void resultFail(String err);
}