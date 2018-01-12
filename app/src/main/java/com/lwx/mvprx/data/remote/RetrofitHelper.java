package com.lwx.mvprx.data.remote;

import com.lwx.mvprx.ApiService;
import com.lwx.mvprx.BuildConfig;
import com.lwx.mvprx.Constant;
import com.lwx.mvprx.MyApplication;
import com.lwx.mvprx.util.FileUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2017/12/20
 *     desc   : Retrofit帮助类
 *     version: 1.0
 * </pre>
 */

public class RetrofitHelper {
    private static volatile RetrofitHelper mRetrofitHelper;
    private ApiService mApiService;

    public static RetrofitHelper getInstance() {
        if (mRetrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (mRetrofitHelper == null) {
                    mRetrofitHelper = new RetrofitHelper();
                }
            }
        }
        return mRetrofitHelper;
    }

    private RetrofitHelper() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //缓存文件
        final File cacheFile = new File(FileUtils.getDiskCacheDir(MyApplication.getInstance()), Constant.CACHE_FILE_NAME);
        //缓存
        final Cache mCache = new Cache(cacheFile, Constant.CACHE_FILE_MAXSIZE);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //NONE(无日志), BASIC(打印请求type,url,request body大小，响应状态和response body的大小),
            // HEADERS(记录请求和响应头，请求类型，url，响应状态), BODY(记录日志请求和响应的请求头和请求体)
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
        }
        //http缓存拦截器
        HttpCacheInterceptor mHttpCacheInterceptor = new HttpCacheInterceptor();
        client.connectTimeout(Constant.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constant.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(mCache)
                .addInterceptor(mHttpCacheInterceptor)
                .addNetworkInterceptor(mHttpCacheInterceptor);
        // 初始化Retrofit
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                // json解析
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    /**
     * 获取ApiService
     *
     * @return ApiService
     */
    public ApiService getApiService() {
        return mApiService;
    }
}
