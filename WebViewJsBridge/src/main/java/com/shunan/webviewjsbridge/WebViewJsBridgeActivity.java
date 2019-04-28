package com.shunan.webviewjsbridge;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebViewJsBridgeActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private JsInterface jsInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();// 隐藏ActionBar
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.webView);
        initWebView();
        webView.loadUrl("http://s.caihuimall.net/eq-cms/policy/index?token=e5793ffd1a7ddb2712a6c2d86fe08bbe");
    }

    private void initWebView() {
        webView.setHorizontalScrollBarEnabled(false);//隐藏横向滚动条
        webView.setVerticalScrollBarEnabled(false); //隐藏纵向滚动条
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//适应屏幕，内容将自动缩放
        webSettings.setUseWideViewPort(false);//这里需要设置为true，才能让Webivew支持<meta>标签的viewport属性
        webSettings.setDatabaseEnabled(false);
        webSettings.setUserAgent(webSettings.getUserAgentString());
        jsInterface = new JsInterface(this);
        webView.addJavascriptInterface(jsInterface, "JsInterface");

        webSettings.setUserAgentString(webSettings.getUserAgentString()
                + ";deviceType/Android"
                + ";VERSION_CODE/" + String.valueOf(BuildConfig.VERSION_CODE)
                + ";VERSION_NAME/" + BuildConfig.VERSION_NAME
                + ";isapp/zyq366app");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("onResume");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                finish();
            }
        }
        return false;
    }
}
