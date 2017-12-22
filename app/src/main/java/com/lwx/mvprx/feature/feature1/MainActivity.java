package com.lwx.mvprx.feature.feature1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.mvprx.R;
import com.lwx.mvprx.base.BaseActivity;
import com.lwx.mvprx.custom.MyDailog;
import com.lwx.mvprx.data.bean.Joke;
import com.lwx.mvprx.util.ToastUtils;

public class MainActivity extends BaseActivity<JokeView, JokePresenter<JokeView>> implements JokeView {
    private TextView jokeInfoTv;
    private Button jokeInfoBt;

    private MyDailog myDailog;

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
        jokeInfoTv = findViewById(R.id.joke_info);
        jokeInfoBt = findViewById(R.id.get_jokes);

        myDailog = new MyDailog(this);
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
        return new JokePresenter(this);
    }

    @Override
    public void showLoading() {
        myDailog.show();
    }

    @Override
    public void hideLoading() {
        myDailog.dismiss();
    }

    @Override
    public void showSuccess(Joke joke) {
        StringBuilder sb = new StringBuilder();
        for (Joke.DataBean dataBean : joke.getData()) {
            sb.append(dataBean.getContent());
            sb.append("\n");
        }
        jokeInfoTv.setText(sb);
    }

    @Override
    public void showFail(String err) {
        ToastUtils.error(this, err);
    }
}
