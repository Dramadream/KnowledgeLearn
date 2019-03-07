package com.fkw.knowledge.net.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/6 17:19 </BR>
 * Desc:            所有的Api的管理类 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class ApiManager1 {

    private static ApiManager1 sInstance;
    private final OkHttpClient okHttpClient;

    private Retrofit mWanAndroidRetrofit;
    private WanAndroidAPi mWanAndroidAPi;

    private ApiManager1() {
        long outTime = 10 * 1000;
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(outTime, TimeUnit.MILLISECONDS)
                .readTimeout(outTime, TimeUnit.MILLISECONDS)
                .writeTimeout(outTime, TimeUnit.MILLISECONDS)
                .build();
    }

    public static ApiManager1 getInstance() {
        if (sInstance == null) {
            synchronized (ApiManager1.class) {
                if (sInstance == null) {
                    sInstance = new ApiManager1();
                }
            }
        }
        return sInstance;
    }

    /**
     * 获得全局唯一OkHttpClient对象
     *
     * @return 全局唯一OkHttpClient对象
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 获取相关Api对象，接着调用对应的短连接方法。
     */
    public WanAndroidAPi getWanAndroidApi() {
        if (mWanAndroidRetrofit == null) {
            mWanAndroidRetrofit = new Retrofit.Builder()
                    .baseUrl(WanAndroidAPi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        if (mWanAndroidAPi == null) {
            mWanAndroidAPi = mWanAndroidRetrofit.create(WanAndroidAPi.class);
        }

        return mWanAndroidAPi;
    }

}
