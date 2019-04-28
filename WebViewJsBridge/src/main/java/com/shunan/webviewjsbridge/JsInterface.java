package com.shunan.webviewjsbridge;

import android.Manifest;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;


public class JsInterface {
    private final int CARMERA_CODE = 112;
    private AppCompatActivity activity;
    public static final int REQUEST_CODE_SCAN = 400;

    public JsInterface(AppCompatActivity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void colse() {
        activity.finish();
    }

    @JavascriptInterface
    public void nativeAlert(String mes) {
        Toast.makeText(activity, mes, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void shareTwoParameter(String url, String type) {
        switch (type) {
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }
    }

    /**
     * 跳转扫码页面
     */
    @JavascriptInterface
    public void goToCapture() {
        PermissionListener listener = new CompositePermissionListener(getPermissionListener(),
                getSnackbarOnDeniedPermissionListener("扫描二维码需要相机权限，请开启。"));
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(listener).check();
    }

    private PermissionListener getPermissionListener() {
        return new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                Intent intent = new Intent(activity, ScanActivity.class);
                activity.startActivityForResult(intent, REQUEST_CODE_SCAN);
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        };
    }

    private SnackbarOnDeniedPermissionListener getSnackbarOnDeniedPermissionListener(String content) {
        return SnackbarOnDeniedPermissionListener.Builder.with(activity.findViewById(android.R.id.content),
                content).withOpenSettingsButton("去设置").withCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar snackbar) {
                // Event handler for when the given Snackbar is visible
            }

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                // Event handler for when the given Snackbar has been dismissed
            }
        }).build();
    }

    @JavascriptInterface
    public void shareFiveParameter(String title, String text, String url, String logourl, String type) {
        switch (type) {
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }

    }

    @JavascriptInterface
    public void shareSixParameter(String title, String text, String url, String logourl, String callback, String type) {
        switch (type) {
            case "微信":
                break;
            case "微信朋友圈":
                break;
            case "微信收藏":
                break;
            case "QQ":
                break;
            case "QQ空间":
                break;
        }

    }


}
