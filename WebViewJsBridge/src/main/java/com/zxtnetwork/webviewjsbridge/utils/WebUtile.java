package com.zxtnetwork.webviewjsbridge.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;

import com.shunan.webviewjsbridge.BuildConfig;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.zxtnetwork.webviewjsbridge.JsInterface;

/**
 * @Description: java类作用描述
 * @Author: 大魔王老杨
 * @Date: 2019-08-20 14:58
 */
public class WebUtile {
    private static WebSettings webSettings;
    private static JsInterface jsInterface;

    public static void initWeb(WebView webView, Activity activity) {
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
        jsInterface = new JsInterface(activity, webView);
        webView.addJavascriptInterface(jsInterface, "JsInterface");

        webSettings.setUserAgentString(webSettings.getUserAgentString()
                + ";deviceType/Android"
                + ";VERSION_CODE/" + BuildConfig.VERSION_CODE
                + ";VERSION_NAME/" + BuildConfig.VERSION_NAME);


    }

}
