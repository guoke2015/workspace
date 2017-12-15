package com.yl.mvpdemo.presenter;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.yl.mvpdemo.bean.BaseObserver;
import com.yl.mvpdemo.bean.BaseResult;
import com.yl.mvpdemo.bean.Joke;
import com.yl.mvpdemo.manager.DataManager;
import com.yl.mvpdemo.view.JokeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 获取快递信息逻辑处理
 * Created by yangle on 2017/6/27.
 */

public class ExpressPresenter extends BasePresenter {

    private JokeView jokeView;
    private DataManager dataManager;

    public ExpressPresenter(JokeView jokeView, LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        this.jokeView = jokeView;
        dataManager = DataManager.getInstance();
    }

    /**
     * 获取笑话大全信息
     * @param page 当前页数,默认1
     * @param pagesize 每次返回条数,默认1,最大20
     * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time 时间戳（10位），如：1418816972
     */
    public void getJokeInfo(int page,int pagesize,String sort,String time) {

        dataManager.getJokeInfo(page,pagesize,sort,time)
                // 在子线程中进行Http访问
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        jokeView.showProgressDialog();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                // UI线程处理返回接口
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        jokeView.hideProgressDialog();
                    }
                })
                // onDestroy取消订阅
                .compose(getProvider().<BaseResult<Joke>>bindUntilEvent(ActivityEvent.DESTROY))
                // 订阅
                .subscribe(new BaseObserver<Joke>() {
                    @Override
                    public void resultSuccess(Joke data) {
                        jokeView.updateView(data);
                    }

                    @Override
                    public void resultFail(String err) {
                        jokeView.showError(err);
                    }
                });
    }
}
