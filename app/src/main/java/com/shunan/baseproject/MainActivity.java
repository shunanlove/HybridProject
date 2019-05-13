package com.shunan.baseproject;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.shunan.baseproject.test.PowerSaveMonitorService;
import com.shunan.baseproject.test.VolumeReceiver;


public class MainActivity extends AppCompatActivity {


    private int currentVolume;
    private int maxVolume;
    private AudioManager mAudioManager;

    private VolumeReceiver volumeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(MainActivity.this, WebViewJsBridgeActivity.class);
//        intent.putExtra("url","file:////android_asset/test.html");
//        startActivity(intent);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //获取系统的Audio管理者
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //最大音量
        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //当前音量
        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND);


//        registerVolumeChangeReceiver();
//        volumeReceiver = new VolumeReceiver();
//        volumeReceiver.init(this);
        Intent  accessIntent = new Intent(MainActivity.this, PowerSaveMonitorService.class);
        accessIntent.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        accessIntent.setAction("com.android.vending");
        startService(accessIntent);
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
//            Log.d("", "音量：" + currentVolume);
            Logger.d("###音量：" + currentVolume + "###");
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, AudioManager.FLAG_PLAY_SOUND);
        }
    }
}
