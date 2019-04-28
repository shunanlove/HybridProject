package com.shunan.webviewjsbridge;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JsInterface {
    private Activity activity;

    public JsInterface(Activity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void colse() {
        activity.finish();
    }

    @JavascriptInterface
    public void nativeAlert(String mes) {
        Toast.makeText(activity, mes, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void shareTwoParameter(String url, String type) {
        switch (type){
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }
    }

    @JavascriptInterface
    public void shareFourParameter(String title, String text, String url, String type) {
        switch (type){
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }
    }

    @JavascriptInterface
    public void shareFiveParameter(String title, String text, String url, String logourl, String type) {
        switch (type){
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }

    }

    @JavascriptInterface
    public void shareSixParameter(String title, String text, String url, String logourl, String callback, String type) {
        switch (type){
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }

    }


}
