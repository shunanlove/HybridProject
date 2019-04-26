package com.shunan.webviewjsbridge;

import android.content.Context;

import com.tencent.smtt.sdk.QbSdk;

public class JsBridge {
    public static void init(Context context) {
        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
    }
}
