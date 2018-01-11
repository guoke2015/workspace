package com.lwx.mvprx.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lwx.mvprx.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyConstraintLayout extends ConstraintLayout implements MyLayout {
    private final String CONTENT = "type_content";
    private final String LOADING = "type_loading";
    private final String EMPTY = "type_empty";
    private final String ERROR = "type_error";

    private LayoutInflater inflater;
    private View view;

    private List<View> contentViews = new ArrayList<>();

    private View loadingState;
    private TextView progressTextView;

    private View emptyState;
    private ImageView emptyStateImageView;
    private TextView emptyStateTitleTextView;

    private View errorState;
    private ImageView errorStateImageView;
    private TextView errorStateTitleTextView;

    private String state = CONTENT;

    public MyConstraintLayout(Context context) {
        super(context);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void showContent() {
        switchState(CONTENT, 0, null, null, Collections.<Integer>emptyList());
    }

    @Override
    public void showContent(List<Integer> idsOfViewsNotToShow) {
        switchState(CONTENT, 0, null, null, idsOfViewsNotToShow);
    }

    @Override
    public void showLoading() {
        switchState(LOADING, "", null, Collections.<Integer>emptyList());
    }

    @Override
    public void showLoading(String title) {
        switchState(LOADING, 0, title, null, Collections.<Integer>emptyList());
    }

    @Override
    public void showLoading(String title, List<Integer> idsOfViewsNotToHide) {
        switchState(LOADING, 0, title, null, idsOfViewsNotToHide);
    }

    @Override
    public void showEmpty(OnClickListener clickListener) {
        switchState(EMPTY, "", clickListener, Collections.<Integer>emptyList());
    }

    @Override
    public void showEmpty(int icon, String title, View.OnClickListener clickListener) {
        switchState(EMPTY, icon, title, clickListener, Collections.<Integer>emptyList());
    }

    @Override
    public void showEmpty(Drawable icon, String title, View.OnClickListener clickListener) {
        switchState(EMPTY, icon, title, clickListener, Collections.<Integer>emptyList());
    }

    @Override
    public void showEmpty(int icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        switchState(EMPTY, icon, title, clickListener, idsOfViewsNotToHide);
    }

    @Override
    public void showEmpty(Drawable icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        switchState(EMPTY, icon, title, clickListener, idsOfViewsNotToHide);
    }

    @Override
    public void showError(String titil, OnClickListener clickListener) {
        switchState(ERROR, titil, clickListener, Collections.<Integer>emptyList());
    }

    @Override
    public void showError(int icon, String title, View.OnClickListener clickListener) {
        switchState(ERROR, icon, title, clickListener, Collections.<Integer>emptyList());
    }

    @Override
    public void showError(Drawable icon, String title, View.OnClickListener clickListener) {
        switchState(ERROR, icon, title, clickListener, Collections.<Integer>emptyList());
    }

    @Override
    public void showError(int icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        switchState(ERROR, icon, title, clickListener, idsOfViewsNotToHide);
    }

    @Override
    public void showError(Drawable icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        switchState(ERROR, icon, title, clickListener, idsOfViewsNotToHide);
    }

    private void switchState(String state, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        this.state = state;
        hideAllStates();
        switch (state) {
            case CONTENT:
                setContentVisibility(true, idsOfViewsNotToHide);
                break;
            case LOADING:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateLoadingView();
                break;
            case EMPTY:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateEmptyView();

                emptyStateImageView.setImageResource(R.drawable.ic_empty_page);
                emptyState.setOnClickListener(clickListener);
                break;
            case ERROR:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateErrorView();

                errorStateImageView.setImageResource(R.drawable.ic_error);
                errorStateTitleTextView.setText(title);
                errorState.setOnClickListener(clickListener);
                break;
            default:
                break;
        }
    }

    private void switchState(String state, int icon, String title,
                             OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        this.state = state;

        hideAllStates();

        switch (state) {
            case CONTENT:
                setContentVisibility(true, idsOfViewsNotToHide);
                break;
            case LOADING:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateLoadingView();

                progressTextView.setText(title);
                break;
            case EMPTY:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateEmptyView();

                emptyStateImageView.setImageResource(icon);
                emptyStateTitleTextView.setText(title);
                emptyState.setOnClickListener(clickListener);
                break;
            case ERROR:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateErrorView();

                errorStateImageView.setImageResource(icon);
                errorStateTitleTextView.setText(title);
                errorState.setOnClickListener(clickListener);
                break;
            default:
                break;
        }
    }

    private void switchState(String state, Drawable icon, String title,
                             OnClickListener clickListener, List<Integer> idsOfViewsNotToHide) {
        this.state = state;

        hideAllStates();

        switch (state) {
            case CONTENT:
                setContentVisibility(true, idsOfViewsNotToHide);
                break;
            case LOADING:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateLoadingView();

                progressTextView.setText(title);
                break;
            case EMPTY:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateEmptyView();

                emptyStateImageView.setImageDrawable(icon);
                emptyStateTitleTextView.setText(title);
                emptyState.setOnClickListener(clickListener);
                break;
            case ERROR:
                setContentVisibility(false, idsOfViewsNotToHide);
                inflateErrorView();

                errorStateImageView.setImageDrawable(icon);
                errorStateTitleTextView.setText(title);
                errorState.setOnClickListener(clickListener);
                break;
            default:
                break;
        }
    }

    private void hideAllStates() {
        hideLoadingView();
        hideEmptyView();
        hideErrorView();
    }

    private void hideLoadingView() {
        if (loadingState != null) {
            loadingState.setVisibility(GONE);
        }
    }

    private void hideEmptyView() {
        if (emptyState != null) {
            emptyState.setVisibility(GONE);
        }
    }

    private void hideErrorView() {
        if (errorState != null) {
            errorState.setVisibility(GONE);
        }
    }

    private void setContentVisibility(boolean visible, List<Integer> skipIds) {
        for (View v : contentViews) {
            if (!skipIds.contains(v.getId())) {
                v.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        }
    }

    private void inflateLoadingView() {
        if (loadingState == null) {
            view = inflater.inflate(R.layout.view_progress, null);
            loadingState = view.findViewById(R.id.layout_loading);
            loadingState.setTag(LOADING);

            progressTextView = findViewById(R.id.progressTextView);

            LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.topToTop = ConstraintSet.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
            layoutParams.startToStart = ConstraintSet.PARENT_ID;
            layoutParams.endToEnd = ConstraintSet.PARENT_ID;

            addView(loadingState, layoutParams);
        } else {
            loadingState.setVisibility(VISIBLE);
        }
    }

    private void inflateEmptyView() {
        if (emptyState == null) {
            view = inflater.inflate(R.layout.view_empty, null);
            emptyState = view.findViewById(R.id.layout_empty);
            emptyState.setTag(EMPTY);

            emptyStateImageView = view.findViewById(R.id.image_icon);
            emptyStateTitleTextView = view.findViewById(R.id.text_title);

            LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.topToTop = ConstraintSet.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
            layoutParams.startToStart = ConstraintSet.PARENT_ID;
            layoutParams.endToEnd = ConstraintSet.PARENT_ID;

            addView(emptyState, layoutParams);
        } else {
            emptyState.setVisibility(VISIBLE);
        }
    }

    private void inflateErrorView() {
        if (errorState == null) {
            view = inflater.inflate(R.layout.view_error, null);
            errorState = view.findViewById(R.id.layout_error);
            errorState.setTag(ERROR);

            errorStateImageView = view.findViewById(R.id.image_icon);
            errorStateTitleTextView = view.findViewById(R.id.text_title);

            LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.topToTop = ConstraintSet.PARENT_ID;
            layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
            layoutParams.startToStart = ConstraintSet.PARENT_ID;
            layoutParams.endToEnd = ConstraintSet.PARENT_ID;

            addView(errorState, layoutParams);
        } else {
            errorState.setVisibility(VISIBLE);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child.getTag() == null || (!child.getTag().equals(LOADING) &&
                !child.getTag().equals(EMPTY) && !child.getTag().equals(ERROR))) {

            contentViews.add(child);
        }
    }

    @Override
    public String getCurrentState() {
        return state;
    }

    @Override
    public boolean isContentCurrentState() {
        return state.equals(CONTENT);
    }

    @Override
    public boolean isLoadingCurrentState() {
        return state.equals(LOADING);
    }

    @Override
    public boolean isEmptyCurrentState() {
        return state.equals(EMPTY);
    }

    @Override
    public boolean isErrorCurrentState() {
        return state.equals(ERROR);
    }
}
