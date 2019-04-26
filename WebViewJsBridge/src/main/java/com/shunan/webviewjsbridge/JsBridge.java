package com.shunan.webviewjsbridge;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.QbSdk;

public class JsBridge {
    public static void init(Context context) {
        QbSdk.initX5Environment(context, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Logger.d("是否启动X5内核：" + b);
            }
        });
    }
}
