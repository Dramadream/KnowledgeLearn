package com.fkw.knowledge.net.api

import com.fkw.knowledge.data.ApiResponse
import com.fkw.knowledge.data.ArticlesData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/3/5 16:50
 * Desc:            WanAndroid相关的Api
 *
 * ModifyTime:
 * ModifyItems:
 * ```
 * @author:         Kevin
 */
interface WanAndroidAPi {

    companion object {
        const val BASE_URL: String = "http://www.wanandroid.com/"
    }


    @GET("article/list/{page}/json")
    fun getHomePageArticles(@Path("page") page: Int): Observable<ApiResponse<ArticlesData>>


}