package com.lwx.mvprx.custom;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.List;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/10
 *     desc   : 自定义Layout
 *     version: 1.0
 * </pre>
 */

public interface MyLayout {
    public void showContent();

    public void showContent(List<Integer> idsOfViewsNotToShow);

    public void showLoading();

    public void showLoading(String title);

    public void showLoading(String title, List<Integer> idsOfViewsNotToHide);

    public void showEmpty(View.OnClickListener clickListener);

    public void showEmpty(int icon, String title, View.OnClickListener clickListener);

    public void showEmpty(Drawable icon, String title, View.OnClickListener clickListener);

    public void showEmpty(int icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    public void showEmpty(Drawable icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    public void showError(String titil,View.OnClickListener clickListener);

    public void showError(int icon, String title, View.OnClickListener clickListener);

    public void showError(Drawable icon, String title, View.OnClickListener clickListener);

    public void showError(int icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    public void showError(Drawable icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    public String getCurrentState();

    public boolean isContentCurrentState();

    public boolean isLoadingCurrentState();

    public boolean isEmptyCurrentState();

    public boolean isErrorCurrentState();
}
