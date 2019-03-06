package com.fkw.knowledge.ui.wan_android

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.fkw.knowledge.R
import com.fkw.knowledge.net.api.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ```
 * author   : FKW
 * time     : 2019/3/5 16:00
 * desc     : WanAndroid文章列表
 * ```
 */
class WanAndroidArticleActivity : AppCompatActivity() {

    private lateinit var srl: SwipeRefreshLayout
    private lateinit var rv: RecyclerView
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wan_android_article)
        initView()
    }

    private fun initView() {
        srl = findViewById(R.id.srl)
        rv = findViewById(R.id.rv)

        disposable = ApiManager.getInstance().wanAndroidApi.getHomePageArticles(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response -> val data = response.data }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}
