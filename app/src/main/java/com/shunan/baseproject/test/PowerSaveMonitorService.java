package com.shunan.baseproject.test;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.orhanobut.logger.Logger;

public class PowerSaveMonitorService extends AccessibilityService {

    private static final String TAG = PowerSaveMonitorService.class.getSimpleName();

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Logger.d(TAG, "onKeyEvent");
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Logger.d(TAG, "KEYCODE_VOLUME_DOWN");
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Logger.d(TAG, "KEYCODE_VOLUME_UP");
                break;
        }
        return super.onKeyEvent(event);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String pkgName = event.getPackageName().toString();
        String className = event.getClassName().toString();
        int eventType = event.getEventType();

        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                break;
        }
    }

}
