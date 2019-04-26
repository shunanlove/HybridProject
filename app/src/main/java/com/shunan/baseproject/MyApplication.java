package com.shunan.baseproject;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.shunan.webviewjsbridge.JsBridge;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JsBridge.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
