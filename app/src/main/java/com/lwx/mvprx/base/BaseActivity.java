package com.lwx.mvprx.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends RxAppCompatActivity {
    //表示层的引用
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= setPresenter();
        mPresenter.attachView((V) this);
    }

    /**
     * 创建表示层
     * @return presenter
     */
    protected abstract P setPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
