package com.yl.mvpdemo.net;

import com.yl.mvpdemo.MyApplication;
import com.yl.mvpdemo.utils.NetworkUtils;

import java.io.IOException;

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
        /*if (NetworkUtils.isAvailableByPing(MyApplication.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(new CacheControl.Builder().maxStale(Constant.NETWORK_IS_AVAILABLE_CACHE_MAXAGE, TimeUnit.SECONDS).build()).build();
        } else {
            request = request.newBuilder()
                    .cacheControl(new CacheControl.Builder().maxStale(Constant.NETWORK_NOT_AVAILABLE_CACHE_MAXAGE, TimeUnit.SECONDS).build()).build();
        }*/
        Response response = chain.proceed(request);

        if (NetworkUtils.isAvailableByPing(MyApplication.getInstance())) {
            int maxAge = 60;//缓存失效时间，单位为秒
            return response.newBuilder()
                    .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            //这段代码设置无效
            int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
            return response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
//        return response;
    }
}
