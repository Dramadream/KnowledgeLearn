package com.fkw.knowledge.net.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.linjiang.pandora.Pandora
import java.util.concurrent.TimeUnit

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/3/5 16:18
 * Desc:            短连接的管理类
 *
 * ModifyTime:
 * ModifyItems:
 * ```
 * @author:         Kevin
 */
class ApiManager private constructor() {


    private val outTime = 1000 * 10L
    private var wanAndroidRetrofit: Retrofit
    var okHttpClient: OkHttpClient

    companion object {
        val instance: ApiManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiManager()
        }
    }

    init {
        val okhttpBuild = OkHttpClient.Builder()
                .connectTimeout(outTime, TimeUnit.MILLISECONDS)
                .readTimeout(outTime, TimeUnit.MILLISECONDS)
                .writeTimeout(outTime, TimeUnit.MILLISECONDS)

        okhttpBuild.addNetworkInterceptor(StethoInterceptor())
        okhttpBuild.addInterceptor(Pandora.get().interceptor)

        okHttpClient = okhttpBuild.build()

        wanAndroidRetrofit = Retrofit.Builder()
                .baseUrl(WanAndroidAPi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /**
     * 获取WanAndroid的Api对象
     */
    fun getWanAndroidApi(): WanAndroidAPi {
        return wanAndroidRetrofit.create(WanAndroidAPi::class.java)
    }


}