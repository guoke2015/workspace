package com.lwx.mvprx.feature.feature1;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.mvprx.R;
import com.lwx.mvprx.base.BaseActivity;
import com.lwx.mvprx.custom.FadeViewAnimProvider;
import com.lwx.mvprx.custom.StateLayout;
import com.lwx.mvprx.data.bean.Joke;
import com.lwx.mvprx.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class MainActivity extends BaseActivity<JokeView, JokePresenter<JokeView>> implements JokeView {
    private TextView jokeInfoTv;
    private Button jokeInfoBt;

    private StateLayout stateLayout;
    private ConstraintLayout conlayout;
//    private MyDailog myDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListener();

    }

    /**
     * 初始化控件
     */
    private void init() {
        stateLayout = findViewById(R.id.stateLayout);
        conlayout = findViewById(R.id.conlayout);
        jokeInfoTv = findViewById(R.id.joke_info);
        jokeInfoBt = findViewById(R.id.get_jokes);

        stateLayout.setViewSwitchAnimProvider(new FadeViewAnimProvider());
//        stateLayout.addView(conlayout);
//        myDailog = new MyDailog(this);
        ToastUtils.init(true);
    }

    /**
     * 设置事件
     */
    private void setListener() {
        jokeInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getJokes(1, 5, "desc", String.valueOf(System.currentTimeMillis() / 1000));
            }
        });
    }

    @Override
    protected JokePresenter<JokeView> creatPresenter() {
        return new JokePresenter();
    }

    @Override
    public void showLoading() {
//        myDailog.show();
        stateLayout.showProgressView();
    }

    @Override
    public void hideLoading() {
//        myDailog.dismiss();
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showSuccess(Joke joke) {
        ToastUtils.success(MainActivity.this, "进入showSuccess方法");
        /*StringBuilder sb = new StringBuilder();
        for (Joke.DataBean dataBean : joke.getData()) {
            sb.append(dataBean.getContent());
            sb.append("\n");
        }*/
        ToastUtils.success(MainActivity.this,"数据加载完，开始执行showContentView方法");
        stateLayout.showContentView();
        ToastUtils.success(MainActivity.this, "执行完showContentView方法");
//        jokeInfoTv.setText(joke.getData().get(0).getContent());
    }

    @Override
    public void showFail(final String err) {
        stateLayout.showErrorView(err);
//        ToastUtils.error(this, err);
    }
}
