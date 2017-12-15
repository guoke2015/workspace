package com.yl.mvpdemo.net;

import com.yl.mvpdemo.BuildConfig;
import com.yl.mvpdemo.Constant;
import com.yl.mvpdemo.MyApplication;
import com.yl.mvpdemo.utils.FileUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 获取RetrofitService实例
 */
public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private ApiService apiService;

    public static RetrofitHelper getInstance() {
        return retrofitHelper == null ? retrofitHelper = new RetrofitHelper() : retrofitHelper;
    }

    private RetrofitHelper() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //缓存文件
        final File cacheFile = new File(FileUtils.getDiskCacheDir(MyApplication.getInstance()), Constant.CACHE_FILE_NAME);
        //缓存
        final Cache cache = new Cache(cacheFile, Constant.CACHE_FILE_MAXSIZE);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            //NONE(无日志), BASIC(打印请求type,url,request body大小，响应状态和response body的大小),
            // HEADERS(记录请求和响应头，请求类型，url，响应状态), BODY(记录日志请求和响应的请求头和请求体)
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
        }
        HttpCacheInterceptor httpCacheInterceptor = new HttpCacheInterceptor();
        client.connectTimeout(Constant.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(Constant.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Constant.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .addInterceptor(httpCacheInterceptor)
                .addNetworkInterceptor(httpCacheInterceptor);
        // 初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.SERVER_URL)
                // json解析
                .addConverterFactory(GsonConverterFactory.create())
                // 支持RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getRetrofitService() {
        return apiService;
    }
}
