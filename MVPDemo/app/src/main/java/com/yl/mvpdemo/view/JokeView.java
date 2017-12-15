package com.yl.mvpdemo.view;

import com.yl.mvpdemo.bean.Joke;

/**
 * Created by yangle on 2017/6/27.
 */

public interface JokeView extends BaseView {

    /**
     * 更新UI
     *
     * @param joke 笑话大全信息
     */
    void updateView(Joke joke);
}
