package com.shunan.baseproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.shunan.baseproject.test.MediaButtonReceiver;
import com.shunan.baseproject.test.SingASongService;


public class MainActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private ComponentName mComponent;
    private MediaSession mSession;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        setContentView(R.layout.activity_main);
//        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.no_kill);
//        mMediaPlayer.setLooping(true);
//        mMediaPlayer.start();

        startService(new Intent(this, SingASongService.class));

        //获得AudioManager对象
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //构造一个ComponentName，MediaButtonReceiver
        mComponent = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());

        //注册一个MediaButtonReceiver广播监听
        mAudioManager.registerMediaButtonEventReceiver(mComponent);


//        Intent intent = new Intent(MainActivity.this, WebViewJsBridgeActivity.class);
//        intent.putExtra("url","file:////android_asset/test.html");
//        startActivity(intent);


//        registerVolumeChangeReceiver();
//        volumeReceiver = new VolumeReceiver();
//        volumeReceiver.init(this);
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
