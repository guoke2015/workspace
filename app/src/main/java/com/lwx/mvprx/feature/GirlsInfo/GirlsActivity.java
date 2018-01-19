package com.lwx.mvprx.feature.GirlsInfo;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lwx.mvprx.R;
import com.lwx.mvprx.base.BaseActivity;
import com.lwx.mvprx.custom.MyRecycleViewScrollHelper;
import com.lwx.mvprx.custom.decoration.MyDecoration;
import com.lwx.mvprx.custom.layout.MyLayout;
import com.lwx.mvprx.data.local.Girls;
import com.lwx.mvprx.util.DateUtil;
import com.lwx.mvprx.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class GirlsActivity extends BaseActivity<GirlsView, GirlsPresenter<GirlsView>> implements GirlsView, MyRecycleViewScrollHelper.OnScrollPositionChangedListener {
    private RecyclerView girlsRec;
    private GirlgRecyclerViewAdapter girlgRecyclerViewAdapter;
    private MyRecycleViewScrollHelper myRecycleViewScrollHelper;

    private MyLayout myLayout;
    //加载更多下标
    private int day = 0;
    private String date = "2018-01-13";

    private List<Girls> girls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls);

        init();
    }

    private void init() {
        myLayout = findViewById(R.id.my_layout);
        girlsRec = findViewById(R.id.girls_rec);

        girls = new ArrayList<>();
        setRecycleView();
        myLayout.showContent();
        mPresenter.queryGirlsFromDate(DateUtil.stringtoDate(date, DateUtil.LONG_DATE_FORMAT));
    }

    @Override
    protected GirlsPresenter<GirlsView> setPresenter() {
        return new GirlsPresenter<>();
    }

    @Override
    public void showSuccess(final List<Girls> list) {
        if (list != null && list.size() > 0) {
            girls.addAll(list);
            myLayout.showContent();
            girlgRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            if (girls.size() > 0) {
                myLayout.showContent();
            } else {
                myLayout.showEmpty(null);
            }
        }

    }

    private void setRecycleView() {
        myRecycleViewScrollHelper = new MyRecycleViewScrollHelper(this);
        myRecycleViewScrollHelper.attachToRecycleView(girlsRec);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        girlsRec.setLayoutManager(manager);
        girlsRec.setHasFixedSize(true);
        girlgRecyclerViewAdapter = new GirlgRecyclerViewAdapter(this, R.layout.girls_rec, girls);
        girlsRec.setAdapter(girlgRecyclerViewAdapter);
        girlsRec.setItemAnimator(new DefaultItemAnimator());
        girlsRec.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        girlgRecyclerViewAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ToastUtils.success(GirlsActivity.this, girls.get(position).getTitle());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
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

    @Override
    public void onScrollToTop() {
        day = 0;
        mPresenter.queryGirlsFromDate(DateUtil.stringtoDate(date, DateUtil.LONG_DATE_FORMAT));
    }

    @Override
    public void onScrollToBottom() {
        day--;
        mPresenter.queryGirlsFromDate(DateUtil.nextDay(DateUtil.stringtoDate(date, DateUtil.LONG_DATE_FORMAT), day));
    }

    @Override
    public void onScrollToUnknown(boolean isTopViewVisible, boolean isBottomViewVisible) {

    }
}
