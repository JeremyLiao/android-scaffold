package com.jeremyliao.android.scaffold.news.modules.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.databinding.ActivityNewsDetailBinding;
import com.jeremyliao.android.scaffold.news.base.BaseActivity;
import com.jeremyliao.android.scaffold.news.beans.gank.Content;
import com.jeremyliao.android.scaffold.news.beans.gank.Site;

public class NewsDetailActivity extends BaseActivity {

    private static final String CONTENT_EXTRA_KEY = "CONTENT_EXTRA_KEY";

    private ActivityNewsDetailBinding binding;
    private Content content;

    public static void launch(Context context, Content content) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(CONTENT_EXTRA_KEY, content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = (Content) getIntent().getSerializableExtra(CONTENT_EXTRA_KEY);
        if (content == null) {
            finish();
            return;
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        binding.setLifecycleOwner(this);
        Site site = content.getSite();
        ActionBar actionBar = getSupportActionBar();
        if (site != null && actionBar != null) {
            actionBar.setTitle(site.getName());
        }
        init();
        loadUrl();
    }

    private void loadUrl() {
        binding.webView.loadUrl(content.getUrl());
    }

    private void init() {
        WebSettings webSettings = binding.webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //优先使用缓存:
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (binding.webView != null) {
            binding.webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            binding.webView.clearHistory();
            binding.webView.destroy();
        }
        super.onDestroy();
    }
}
