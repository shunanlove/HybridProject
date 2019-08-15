package com.zxtnetwork.webviewjsbridge;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.shunan.webviewjsbridge.BuildConfig;
import com.shunan.webviewjsbridge.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import static com.zxtnetwork.webviewjsbridge.JsInterface.REQUEST_CODE_CHOOSE;
import static com.zxtnetwork.webviewjsbridge.JsInterface.REQUEST_CODE_SCAN;

public class WebViewJsBridgeActivity extends AppCompatActivity {
    private static final String OPEN = "open://";

    private WebView webView;
    private WebSettings webSettings;
    private JsInterface jsInterface;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();// 隐藏ActionBar
        setContentView(R.layout.activity_web_view);

        extras = getIntent().getExtras();
        webView = findViewById(R.id.webView);
        initWebView();
        webView.loadUrl(extras.getString("url"));

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
//        webSettings.setUserAgent(webSettings.getUserAgentString());
        jsInterface = new JsInterface(this, webView);
        webView.addJavascriptInterface(jsInterface, "JsInterface");

        webSettings.setUserAgentString(webSettings.getUserAgentString()
                + ";deviceType/Android"
                + ";VERSION_CODE/" + BuildConfig.VERSION_CODE
                + ";VERSION_NAME/" + BuildConfig.VERSION_NAME);

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

    List<Uri> mSelected;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("result");
                webView.loadUrl("javascript:scanQrCode_callBack('" + content + "')");
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            ArrayList<String> resList = new ArrayList<>();
            for (Uri uri : mSelected) {
                resList.add(uri.toString());
            }
            webView.loadUrl("javascript:pictureSelector_callBack('" + new Gson().toJson(resList) + "')");
        }
    }
}
