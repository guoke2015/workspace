package com.yl.mvpdemo.net;

import com.yl.mvpdemo.Constant;
import com.yl.mvpdemo.MyApplication;
import com.yl.mvpdemo.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/12
 *     desc   : http缓存拦截器
 *     version: 1.0
 * </pre>
 */
public class HttpCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtils.isAvailableByPing(MyApplication.getInstance())) {
            Response response = chain.proceed(request);
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + Constant.NETWORK_IS_AVAILABLE_CACHE_MAXAGE)
                    .build();
        } else {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();

            Response response = chain.proceed(request);
            //下面注释的部分设置也没有效果，因为在上面已经设置了
            return response.newBuilder()
//                        .removeHeader("Pragma")
//                        .removeHeader("Cache-Control")
//                        .header("Cache-Control", "public, only-if-cached, max-stale="+Constant.NETWORK_NOT_AVAILABLE_CACHE_MAXAGE)
                    .build();
        }
    }
}
