package com.shunan.baseproject.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.orhanobut.logger.Logger;

public class MediaButtonReceiver extends BroadcastReceiver {
    private static String TAG = "MediaButtonReceiver";
    private static long currentTime = 0L;
    private static int currentKeycode = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 获得KeyEvent对象
        KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

        if (Intent.ACTION_MEDIA_BUTTON.equals(action)) {
            // 获得按键码
            int keycode = event.getKeyCode();

            if (keycode == MediaButtonReceiver.currentKeycode && System.currentTimeMillis() - MediaButtonReceiver.currentTime < 2000) {
                return;
            }
            MediaButtonReceiver.currentKeycode = keycode;
            MediaButtonReceiver.currentTime = System.currentTimeMillis();


            switch (keycode) {
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    //播放下一首
                    Logger.d("下一首");
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    //播放上一首
                    Logger.d("上一首");
                    break;
                case KeyEvent.KEYCODE_HEADSETHOOK:
                    //中间按钮,暂停or播放
                    //可以通过发送一个新的广播通知正在播放的视频页面,暂停或者播放视频
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    Logger.d("播放");
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    Logger.d("暂停");
                    break;
                default:
                    break;
            }
        }
    }
}
