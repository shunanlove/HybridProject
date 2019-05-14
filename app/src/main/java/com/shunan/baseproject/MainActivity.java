package com.shunan.baseproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.shunan.baseproject.test.MediaButtonReceiver;
import com.shunan.baseproject.test.SingASongService;


public class MainActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private ComponentName mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        setContentView(R.layout.activity_main);

        startService(new Intent(this, SingASongService.class));

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //构造一个ComponentName，MediaButtonReceiver
        mComponent = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());

        //注册一个MediaButtonReceiver广播监听
        mAudioManager.registerMediaButtonEventReceiver(mComponent);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        Intent intent = new Intent(MainActivity.this, WebViewJsBridgeActivity.class);
//        intent.putExtra("url","file:////android_asset/test.html");
//        startActivity(intent);

//        registerVolumeChangeReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterVolumeChangeReceiver();
        //注销方法
        mAudioManager.unregisterMediaButtonEventReceiver(mComponent);

    }

    private SettingsContentObserver mSettingsContentObserver;

    private void registerVolumeChangeReceiver() {
        mSettingsContentObserver = new SettingsContentObserver(this, new Handler());
        getContentResolver()
                .registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mSettingsContentObserver);
    }

    private void unregisterVolumeChangeReceiver() {
        this.getContentResolver().unregisterContentObserver(mSettingsContentObserver);
    }

    public class SettingsContentObserver extends ContentObserver {
        Context context;

        public SettingsContentObserver(Context c, Handler handler) {
            super(handler);
            context = c;
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            Logger.d("###音量：" + currentVolume + "###");
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, AudioManager.FLAG_PLAY_SOUND);
        }
    }

}
