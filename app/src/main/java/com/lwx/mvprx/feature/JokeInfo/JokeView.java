package com.lwx.mvprx.feature.JokeInfo;

import com.lwx.mvprx.base.IBaseView;
import com.lwx.mvprx.data.bean.Joke;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : 笑话大全接口类(V层)
 *     version: 1.0
 * </pre>
 */

public interface JokeView extends IBaseView {
    /**
     * 显示数据
     *
     * @param joke 笑话大全信息
     */
    void showSuccess(Joke joke);

    /**
     * 显示失败信息
     *
     * @param err
     */
    void showFail(String err);
}
