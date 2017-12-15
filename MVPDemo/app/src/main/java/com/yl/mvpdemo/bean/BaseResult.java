package com.yl.mvpdemo.bean;

import java.io.Serializable;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/11
 *     desc   : 返回结果基类
 *     version: 1.0
 * </pre>
 */

public class BaseResult<T> implements Serializable {
    //返回码
    private int error_code;
    //返回说明
    private String reason;
    //返回结果集
    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
