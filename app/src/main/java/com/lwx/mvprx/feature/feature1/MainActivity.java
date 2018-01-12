package com.lwx.mvprx.feature.feature1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.mvprx.R;
import com.lwx.mvprx.base.BaseActivity;
import com.lwx.mvprx.custom.MyLayout;
import com.lwx.mvprx.data.bean.Joke;
import com.lwx.mvprx.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;

public class MainActivity extends BaseActivity<JokeView, JokePresenter<JokeView>> implements JokeView {
    private TextView jokeInfoTv;
    private Button jokeInfoBt;

    private MyLayout myLayout;

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
        myLayout = findViewById(R.id.my_layout);
        jokeInfoTv = findViewById(R.id.joke_info);
        jokeInfoBt = findViewById(R.id.get_jokes);

        myLayout.showContent();
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
        myLayout.showLoading();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void showSuccess(Joke joke) {
        if (joke.getData().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Joke.DataBean dataBean : joke.getData()) {
                sb.append(dataBean.getContent());
                sb.append("\n");
            }
            jokeInfoTv.setText(sb);
            myLayout.showContent();
        } else {
            myLayout.showEmpty(retryOnClickListener);
        }

    }

    @Override
    public void showFail(final String err) {
        myLayout.showError(err, retryOnClickListener);
    }

    /**
     * 定义自定义点击事件
     */
    private View.OnClickListener retryOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPresenter.getJokes(1, 5, "desc", String.valueOf(System.currentTimeMillis() / 1000));
        }
    };
}
