package com.yl.mvpdemo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yl.mvpdemo.Constant;
import com.yl.mvpdemo.R;
import com.yl.mvpdemo.bean.Joke;
import com.yl.mvpdemo.presenter.ExpressPresenter;
import com.yl.mvpdemo.view.JokeView;

/**
 * 主页
 * Created by yangle on 2017/6/26.
 * <p>
 * Website：http://www.yangle.tech
 * GitHub：https://github.com/alidili
 * CSDN：http://blog.csdn.net/kong_gu_you_lan
 * JianShu：http://www.jianshu.com/u/34ece31cd6eb
 */

public class MainActivity extends BaseActivity implements JokeView, View.OnClickListener {

    TextView tvPostInfo;
    Button btnGetPostInfo;

    private ProgressDialog progressDialog;
    private ExpressPresenter expressPresenter;

    private long time = 1418816972;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        expressPresenter = new ExpressPresenter(this, this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在获取笑话大全信息...");
    }

    private void initView() {
        tvPostInfo = (TextView) findViewById(R.id.tv_post_info);
        btnGetPostInfo= (Button) findViewById(R.id.btn_get_post_info);
        btnGetPostInfo.setOnClickListener(this);
    }

    @Override
    public void updateView(Joke joke) {
        tvPostInfo.setText(joke.getData().get(0).getContent());
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_post_info:
                time+=1000;
                expressPresenter.getJokeInfo(1,1, Constant.DESC,time+"");
                break;
            default:

                break;
        }
    }
}
