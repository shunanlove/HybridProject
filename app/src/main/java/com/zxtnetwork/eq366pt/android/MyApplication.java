package com.zxtnetwork.eq366pt.android;

import android.app.Application;
import android.content.Context;

import com.zxtnetwork.webviewjsbridge.JsConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JsConfig.init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
