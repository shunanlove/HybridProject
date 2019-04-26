package com.shunan.webviewjsbridge;

import android.app.Activity;
import android.webkit.JavascriptInterface;

public class JsInterface {
    private Activity activity;

    public JsInterface(Activity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void colse() {
        activity.finish();
    }

}
