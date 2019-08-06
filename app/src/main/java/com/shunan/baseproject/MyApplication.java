package com.shunan.baseproject;

import android.app.Application;

import com.shunan.webviewjsbridge.JsConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JsConfig.init(this);
    }
}
