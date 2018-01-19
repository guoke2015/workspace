package com.lwx.mvprx.feature.GirlsInfo;

import com.lwx.mvprx.base.IBaseView;
import com.lwx.mvprx.data.local.Girls;

import java.util.List;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/18
 *     desc   : 美女图片信息接口类（V层）
 *     version: 1.0
 * </pre>
 */

public interface GirlsView extends IBaseView {
    /**
     * 显示成功信息
     *
     * @param list
     */
    void showSuccess(List<Girls> list);

    /**
     * 显示失败信息
     *
     * @param err 失败信息
     */
    void showFail(String err);
}
