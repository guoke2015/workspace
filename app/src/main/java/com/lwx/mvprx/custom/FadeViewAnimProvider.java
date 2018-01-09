package com.lwx.mvprx.custom;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/01/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class FadeViewAnimProvider implements ViewAnimProvider {

    @Override
    public Animation showAnimation() {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(200);
        animation.setInterpolator(new DecelerateInterpolator());
        return animation;
    }

    @Override
    public Animation hideAnimation() {
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(200);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        return animation;
    }
}
