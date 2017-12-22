package com.lwx.mvprx.base;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : Presenter基类(表示层)
 *     version: 1.0
 * </pre>
 */

public class BasePresenter<V> {
    //View层引用
    protected WeakReference<V> mViewRef;

    /**
     * 绑定
     * @param view
     */
    public void attachView(V view){
        mViewRef=new WeakReference<V>(view);
    }

    /**
     * 解绑
     */
    public void detachView(){
        mViewRef.clear();
    }

    private LifecycleProvider<ActivityEvent> provider;

    public BasePresenter(LifecycleProvider<ActivityEvent> provider) {
        this.provider = provider;
    }

    public LifecycleProvider<ActivityEvent> getProvider() {
        return provider;
    }
}
