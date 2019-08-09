package com.zxtnetwork.webviewjsbridge;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.zxtnetwork.webviewjsbridge.listener.MyPermissionListener;
import com.zxtnetwork.webviewjsbridge.module.ShareData;
import com.zxtnetwork.webviewjsbridge.utils.Glide4Engine;
import com.zxtnetwork.webviewjsbridge.utils.MyUtils;
import com.zxtnetwork.webviewjsbridge.utils.ShareUtils;
import com.tencent.smtt.sdk.WebView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;


public class JsInterface {
    private final int CARMERA_CODE = 112;
    private Activity activity;
    public static final int REQUEST_CODE_SCAN = 400;
    public static final int REQUEST_CODE_CHOOSE = 500;
    private WebView webView;

    public JsInterface(Activity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    /**
     * 关闭当前界面
     */
    @JavascriptInterface
    public void close() {
        activity.finish();
    }

    /**
     * 弹出Toast
     *
     * @param mes //提示文字内容
     */
    @JavascriptInterface
    public void nativeAlert(String mes) {
        Toast.makeText(activity, mes, Toast.LENGTH_LONG).show();
    }

    /**
     * 跳转扫码页面
     */
    @JavascriptInterface
    public void scanQrCode() {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new MyPermissionListener(activity, "扫描二维码需要相机权限，请开启。") {
                    @Override
                    public void authorizationSucceeded(PermissionGrantedResponse response) {
                        Intent intent = new Intent(activity, ScanActivity.class);
                        activity.startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }
                }).check();
    }

    /**
     * 分享
     *
     * @param shareJson
     */
    @JavascriptInterface
    public void shareParameter(String shareJson) {
        ShareData mJson = new Gson().fromJson(shareJson, ShareData.class);
        ShareUtils.showShare(activity, mJson);


//        switch (mJson.getType()) {
//            case "Wechat":
//                //微信好友
//                ShareUtils.showShare(activity, mJson);
//                break;
//            case "WechatMoments":
//                //微信朋友圈
//                ShareUtils.showShare(activity, mJson);
//                break;
//            case "WechatFavorite":
//                //微信收藏
//                ShareUtils.showShare(activity, mJson);
//                break;
//            case "QQ":
//                //QQ好友
//                ShareUtils.showShare(activity, mJson);
//                break;
//            case "QZone":
//                //QQ空间
//                ShareUtils.showShare(activity, mJson);
//                break;
//        }
    }


    /**
     * 跳转百度地图
     *
     * @param mLat        //经度
     * @param mLng        //维度
     * @param mAddressStr //目的地
     */
    @JavascriptInterface
    public void goToBaiduMap(String mLat, String mLng, String mAddressStr) {
        if (!MyUtils.isInstalled(activity, "com.baidu.BaiduMap")) {
            Toast.makeText(activity, "请先安装百度地图客户端", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
                + mLat + "," + mLng + "|name:" + mAddressStr + // 终点
                "&mode=driving" + // 导航路线方式
                "&src=" + activity.getPackageName()));
        activity.startActivity(intent); // 启动调用
    }

    /**
     * 跳转高德地图
     *
     * @param mLat        //经度
     * @param mLng        //维度
     * @param mAddressStr //目的地
     */
    @JavascriptInterface
    public void goToGaodeMap(String mLat, String mLng, String mAddressStr) {
        if (!MyUtils.isInstalled(activity, "com.autonavi.minimap")) {
            Toast.makeText(activity, "请先安装高德地图客户端", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=").append(activity.getPackageName());
        stringBuffer.append("&lat=").append(mLat)
                .append("&lon=").append(mLng).append("&keywords=" + mAddressStr)
                .append("&dev=").append(0)
                .append("&style=").append(2);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        activity.startActivity(intent);
    }

    /**
     * 跳转腾讯地图
     *
     * @param mLat        //经度
     * @param mLng        //维度
     * @param mAddressStr //目的地
     */
    @JavascriptInterface
    public void goToTencentMap(String mLat, String mLng, String mAddressStr) {
        if (!MyUtils.isInstalled(activity, "com.tencent.map")) {
            Toast.makeText(activity, "请先安装腾讯地图客户端", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("qqmap://map/routeplan?type=drive")
                .append("&tocoord=").append(mLat).append(",").append(mLng).append("&to=" + mAddressStr);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        activity.startActivity(intent);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    @JavascriptInterface
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        activity.startActivity(intent);
    }

    @JavascriptInterface
    public void pictureSelector() {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MyPermissionListener(activity, "需要权限，请开启。") {
                    @Override
                    public void authorizationSucceeded(PermissionGrantedResponse response) {
                        Matisse.from(activity)
                                .choose(MimeType.ofImage())
                                .countable(true)
                                .showSingleMediaType(true)
                                .maxSelectable(9)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(new Glide4Engine())
                                .forResult(REQUEST_CODE_CHOOSE);
                    }
                }).check();
    }

}