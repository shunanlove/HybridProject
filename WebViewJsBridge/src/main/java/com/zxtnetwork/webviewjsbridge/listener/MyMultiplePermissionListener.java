package com.shunan.webviewjsbridge.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public abstract class MyMultiplePermissionListener implements MultiplePermissionsListener {

    private Context context;
    private String msg;

    public MyMultiplePermissionListener(Context context, String msg) {
        this.context = context;
        this.msg = msg;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        if (!report.areAllPermissionsGranted()) {
            new AlertDialog.Builder(context)
                    .setTitle("提醒")
                    .setMessage(msg)
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.parse("package:" + context.getPackageName()));
                            myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                            myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(myAppSettings);
                        }
                    }).show();
        } else {
            authorizationSucceeded(report);
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
        token.continuePermissionRequest();
    }


    public abstract void authorizationSucceeded(MultiplePermissionsReport report);
}
