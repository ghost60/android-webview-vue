package com.example.zyl.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
        // super.onReceivedSslError(view, handler, error);
        // 接受所有网站的证书，忽略SSL错误，执行访问网页
        handler.proceed();
    }
}
