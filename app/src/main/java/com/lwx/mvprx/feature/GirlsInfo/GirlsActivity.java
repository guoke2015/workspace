package com.lwx.mvprx.feature.GirlsInfo;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lwx.mvprx.R;
import com.lwx.mvprx.base.BaseActivity;
import com.lwx.mvprx.custom.decoration.MyDecoration;
import com.lwx.mvprx.custom.layout.MyLayout;
import com.lwx.mvprx.util.DateUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

public class GirlsActivity extends BaseActivity<GirlsView, GirlsPresenter<GirlsView>> implements GirlsView {
    private RecyclerView girlsRec;
    private GirlgRecyclerViewAdapter girlgRecyclerViewAdapter;

    private MyLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls);

        myLayout = findViewById(R.id.my_layout);
        girlsRec = findViewById(R.id.girls_rec);

        myLayout.showContent();
        mPresenter.queryGirlsFromDate(DateUtil.stringtoDate("2018-01-13", DateUtil.LONG_DATE_FORMAT));
    }

    @Override
    protected GirlsPresenter<GirlsView> setPresenter() {
        return new GirlsPresenter<>();
    }

    @Override
    public void showSuccess(List list) {
        if (list != null && list.size() > 0) {
            RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
            girlsRec.setLayoutManager(manager);
            girlsRec.setHasFixedSize(true);
            girlgRecyclerViewAdapter = new GirlgRecyclerViewAdapter(this, R.layout.girls_rec, list);
            girlsRec.setAdapter(girlgRecyclerViewAdapter);
            girlsRec.setItemAnimator(new DefaultItemAnimator());
            girlsRec.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
            myLayout.showContent();
        } else {
            myLayout.showEmpty(null);
        }

    }

    @Override
    public void showFail(String err) {
        myLayout.showError(err, null);
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
}
