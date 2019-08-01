package com.shunan.webviewjsbridge;

import android.content.Context;

import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.QbSdk;

public class JsConfig {
    public static void init(Context context) {
        //初始化shareSdk
        MobSDK.init(context);

        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Logger.d("是否启动X5内核：" + b);
            }
        });
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
