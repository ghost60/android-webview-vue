package com.example.zyl.webapp;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始加载时
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                //页面加载结束时
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // 这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                /**
                 * 网页跳转：
                 * 1.在当前的webview跳转到新连接
                 * view.loadUrl(url);
                 * 2.调用系统浏览器跳转到新网页
                 * Intent i = new Intent(Intent.ACTION_VIEW);
                 * i.setData(Uri.parse(url));
                 * startActivity(i);
                 */
                return true;
            }
        });

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);// 支持JS
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过js打开新的窗口
        webSettings.setBuiltInZoomControls(false);// 设置支持缩放
        webSettings.setDomStorageEnabled(true);//使用localStorage则必须打开
        webSettings.setSupportMultipleWindows(false);// 设置同一个界面
        webSettings.setNeedInitialFocus(false);// 禁止webview上面控件获取焦点(黄色边框)
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);


//        mWebView.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
//        mWebView.setBackgroundResource(R.color.colorAccent);


        // REMOTE RESOURCE
//         mWebView.loadUrl("http://www.baidu.com");
//         mWebView.setWebViewClient(new MyWebViewClient());

        //忽略跨域
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }else{
            try {
                Class<?> clazz = mWebView.getSettings().getClass();
                Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(mWebView.getSettings(), true);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // LOCAL RESOURCE
        mWebView.loadUrl("file:///android_asset/index.html");
//         mWebView.setWebViewClient(new MyWebViewClient());


//        mWebView.clearHistory();
//        mWebView.clearCache(true);
//        mWebView.clearFormData();
//        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);// 隐藏滚动条webView.requestFocus();
//        mWebView.requestFocusFromTouch();

        //浏览器中调试
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            mWebView.setWebContentsDebuggingEnabled(true);
        }

    }
}
