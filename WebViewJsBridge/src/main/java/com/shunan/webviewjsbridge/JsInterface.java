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
    public void shareWxUrl(String img, String title, String content) {

    }
}
