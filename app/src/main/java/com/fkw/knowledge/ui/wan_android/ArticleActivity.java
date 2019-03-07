package com.fkw.knowledge.ui.wan_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fkw.knowledge.R;
import com.fkw.knowledge.base.BaseActivity;
import com.fkw.knowledge.data.ApiResponse;
import com.fkw.knowledge.data.Article;
import com.fkw.knowledge.data.ArticlesData;
import com.fkw.knowledge.net.api.ApiManager;
import com.fkw.knowledge.net.api.ErrorConsumer;
import com.fkw.knowledge.ui.web.WebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 文章列表
 */
public class ArticleActivity extends BaseActivity {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView rv;
    private BaseQuickAdapter<Article, BaseViewHolder> mAdapter;
    private List<Article> mData;
    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initView();
    }

    private void initView() {
        smartRefreshLayout = findViewById(R.id.smart_refresh_layout);
        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new BaseQuickAdapter<Article, BaseViewHolder>(R.layout.itemview_article, mData) {
            @Override
            protected void convert(BaseViewHolder helper, Article item) {
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_author, item.getAuthor());
                String time = TimeUtils.getFriendlyTimeSpanByNow(item.getPublishTime());
                helper.setText(R.id.tv_time, time);
            }
        };

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toWebViewActivity(mAdapter.getItem(position).getLink());
            }
        });
        rv.setAdapter(mAdapter);

        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getLoadMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });

    }


    /**
     * 初始化，或者下拉刷新，获取数据
     */
    private void initData() {
        Consumer<ApiResponse<ArticlesData>> refreshConsumer = new Consumer<ApiResponse<ArticlesData>>() {
            @Override
            public void accept(ApiResponse<ArticlesData> response) throws Exception {
                mAdapter.setNewData(response.getData().getDatas());
                mCurrentPage = response.getData().getCurPage();
                checkDataOver(response.getData());
                smartRefreshLayout.finishRefresh();
            }
        };

        Disposable refreshDisposable = getArticleObservable(0).subscribe(refreshConsumer, new ErrorConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                smartRefreshLayout.finishRefresh();

            }
        });
        addDisposable(refreshDisposable);
    }


    /**
     * 上拉更多时的数据
     */
    private void getLoadMoreData() {
        Consumer<ApiResponse<ArticlesData>> loadMoreConsumer = new Consumer<ApiResponse<ArticlesData>>() {
            @Override
            public void accept(ApiResponse<ArticlesData> response) throws Exception {
                mAdapter.addData(response.getData().getDatas());
                mCurrentPage = response.getData().getCurPage();
                smartRefreshLayout.finishLoadMore();
            }
        };

        Disposable loadMoreDisposable = getArticleObservable(mCurrentPage).subscribe(loadMoreConsumer, new ErrorConsumer() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                smartRefreshLayout.finishLoadMore();
            }
        });
        addDisposable(loadMoreDisposable);
    }

    private Observable<ApiResponse<ArticlesData>> getArticleObservable(int page) {
        //return ApiManager1.getInstance().getWanAndroidApi().getHomePageArticles(page)
        return ApiManager.Companion.getInstance().getWanAndroidApi().getHomePageArticles(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 检测文章是否被加载完
     *
     * @param data
     */
    @SuppressLint("RestrictedApi")
    private void checkDataOver(ArticlesData data) {
        smartRefreshLayout.getRefreshFooter().setNoMoreData(data.getOver());
    }

    /**
     * 跳转到WebViewActivity
     *
     * @param link 要跳转的链接
     */
    private void toWebViewActivity(String link) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(WebActivity.WEB_URL, link);
        startActivity(intent);
    }

}
