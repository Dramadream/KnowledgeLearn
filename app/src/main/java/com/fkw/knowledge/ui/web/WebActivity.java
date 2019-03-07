package com.fkw.knowledge.ui.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fkw.knowledge.R;
import com.fkw.knowledge.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/6 17:46 </BR>
 * Desc:            WebView的Activity </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class WebActivity extends BaseActivity {
    public static final String WEB_URL = "web_url";

    private SmartRefreshLayout smartRefreshLayout;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        String intentUrl = getIntent().getStringExtra(WEB_URL);


        webView.loadUrl(intentUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            @SuppressWarnings("deprecation")
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //loading.showContent();
            }
        });
    }

    private void initView() {
        smartRefreshLayout = findViewById(R.id.smart_refresh_layout);
        webView = findViewById(R.id.web_view);

        smartRefreshLayout.setEnableLoadMore(false);
    }

}
