package com.shunan.baseproject;

import android.app.Application;

import com.mob.MobSDK;
import com.shunan.webviewjsbridge.JsConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化shareSdk
        MobSDK.init(this);
        JsConfig.init(this);
    }
}
