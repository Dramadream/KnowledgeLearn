package com.fkw.knowledge.net.api

import com.fkw.knowledge.data.ApiResponse
import com.fkw.knowledge.data.Article
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/3/5 16:50
 * Desc:            TODO
 *
 * ModifyTime:
 * ModifyItems:
 * ```
 * @author:         Kevin
 */
interface WanAndroidAPi {

    companion object {
        const val BASE_URL: String = "http://www.wanandroid.com";
    }


    @GET("article/list/{page}/json")
    fun getHomePageArticles(page: Int): Observable<ApiResponse<Article>>


}