package com.zxtnetwork.eq366pt.android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.orhanobut.logger.Logger;
import com.zxtnetwork.eq366pt.android.test.MediaButtonReceiver;
import com.zxtnetwork.eq366pt.android.test.SingASongService;
import com.zxtnetwork.webviewjsbridge.ScanActivity;
import com.zxtnetwork.webviewjsbridge.WebViewJsBridgeActivity;
import com.zxtnetwork.webviewjsbridge.listener.MyPermissionListener;

import static com.zxtnetwork.webviewjsbridge.JsInterface.REQUEST_CODE_SCAN;


public class MainActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private ComponentName mComponent;
    @SuppressLint("InvalidWakeLockTag")
    private PowerManager.WakeLock wl;
    private float touch_x;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        setContentView(R.layout.activity_main);
//        butterknife
        Intent intent = new Intent(MainActivity.this, WebViewJsBridgeActivity.class);
        intent.putExtra("url", "file:////android_asset/test.html");
        startActivity(intent);
//
//        registerVolumeChangeReceiver();


//        findViewById(R.id.btn_scan_conn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scanQrCode();
//            }
//        });
//
//        findViewById(R.id.btn_left).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaButtonReceiver.sendMsg(MainActivity.this, MediaButtonReceiver.TO_LEFT);
//            }
//        });
//
//        findViewById(R.id.btn_right).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MediaButtonReceiver.sendMsg(MainActivity.this, MediaButtonReceiver.TO_RIGHT);
//
//            }
//        });

//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
//        wl.acquire();

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
        wl.release();

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

    public void scanQrCode() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new MyPermissionListener(this, "扫描二维码需要相机权限，请开启。") {
                    @Override
                    public void authorizationSucceeded(PermissionGrantedResponse response) {
                        Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }
                }).check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("result");
                MediaButtonReceiver.IP = content.split(":")[0];

                findViewById(R.id.btn_left).setEnabled(true);
                findViewById(R.id.btn_right).setEnabled(true);


                startService(new Intent(this, SingASongService.class));

                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                //构造一个ComponentName，MediaButtonReceiver
                mComponent = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());

                //注册一个MediaButtonReceiver广播监听
                mAudioManager.registerMediaButtonEventReceiver(mComponent);

                findViewById(R.id.cl_root).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        //继承了Activity的onTouchEvent方法，直接监听点击事件
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            //当手指按下的时候
                            touch_x = event.getX();
                        }
                        if (event.getAction() == MotionEvent.ACTION_MOVE) {

                        }
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (touch_x - event.getX() > 50) {
                                MediaButtonReceiver.sendMsg(MainActivity.this, MediaButtonReceiver.TO_LEFT);
                            } else if (touch_x - event.getX() < -50) {
                                MediaButtonReceiver.sendMsg(MainActivity.this, MediaButtonReceiver.TO_RIGHT);
                            }
                        }
                        return true;
                    }
                });
            }
        }
    }
}
