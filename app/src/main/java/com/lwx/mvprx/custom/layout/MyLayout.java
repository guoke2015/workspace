package com.lwx.mvprx.custom.layout;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.List;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/10
 *     desc   : 自定义Layout
 *     version: 1.0
 * </pre>
 */

public interface MyLayout {
    /**
     * 显示内容页面
     */
    void showContent();

    /**
     * 显示内容页面
     *
     * @param idsOfViewsNotToShow
     */
    void showContent(List<Integer> idsOfViewsNotToShow);

    /**
     * 显示正在加载页
     */
    void showLoading();

    /**
     * 显示正在加载页
     *
     * @param title 加载内容
     */
    void showLoading(String title);

    /**
     * 显示正在加载页
     *
     * @param title               加载内容
     * @param idsOfViewsNotToHide
     */
    void showLoading(String title, List<Integer> idsOfViewsNotToHide);

    /**
     * 显示空白页
     *
     * @param clickListener 页面点击事件
     */
    void showEmpty(View.OnClickListener clickListener);

    /**
     * 显示空白页
     *
     * @param icon          图片
     * @param title         空白页内容
     * @param clickListener 页面点击事件
     */
    void showEmpty(int icon, String title, View.OnClickListener clickListener);

    /**
     * 显示空白页
     *
     * @param icon          图片
     * @param title         空白页内容
     * @param clickListener 页面点击事件
     */
    void showEmpty(Drawable icon, String title, View.OnClickListener clickListener);

    /**
     * 显示空白页
     *
     * @param icon                图片
     * @param title               空白页内容
     * @param clickListener       页面点击事件
     * @param idsOfViewsNotToHide
     */
    void showEmpty(int icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    /**
     * 显示空白页
     *
     * @param icon                图片
     * @param title               空白页内容
     * @param clickListener       页面点击事件
     * @param idsOfViewsNotToHide
     */
    void showEmpty(Drawable icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    /**
     * 显示错误页
     *
     * @param titil         错误内容
     * @param clickListener 页面点击事件
     */
    void showError(String titil, View.OnClickListener clickListener);

    /**
     * 显示错误页
     *
     * @param icon          图片
     * @param title         错误内容
     * @param clickListener 页面点击事件
     */
    void showError(int icon, String title, View.OnClickListener clickListener);

    /**
     * 显示错误页
     *
     * @param icon          图片
     * @param title         错误内容
     * @param clickListener 页面点击事件
     */
    void showError(Drawable icon, String title, View.OnClickListener clickListener);

    /**
     * 显示错误页
     *
     * @param icon                图片
     * @param title               错误内容
     * @param clickListener       页面点击事件
     * @param idsOfViewsNotToHide
     */
    void showError(int icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    /**
     * 显示错误页
     *
     * @param icon                图片
     * @param title               错误内容
     * @param clickListener       页面点击事件
     * @param idsOfViewsNotToHide
     */
    void showError(Drawable icon, String title, View.OnClickListener clickListener, List<Integer> idsOfViewsNotToHide);

    /**
     * 获取当前页面
     *
     * @return
     */
    String getCurrentState();

    /**
     * 当前页面是否是显示内容页面
     *
     * @return true:是 false:否
     */
    boolean isContentCurrentState();

    /**
     * 当前页面是否是正在加载页面
     *
     * @return true:是 false:否
     */
    boolean isLoadingCurrentState();

    /**
     * 当前页面是否是空白页面
     *
     * @return true:是 false:否
     */
    boolean isEmptyCurrentState();

    /**
     * 当前页面是否是错误页面
     *
     * @return true:是 false:否
     */
    boolean isErrorCurrentState();
}
