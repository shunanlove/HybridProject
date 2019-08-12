package com.zxtnetwork.webviewjsbridge;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.WebView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zxtnetwork.webviewjsbridge.listener.MyMultiplePermissionListener;
import com.zxtnetwork.webviewjsbridge.listener.MyPermissionListener;
import com.zxtnetwork.webviewjsbridge.model.NameValuePair;
import com.zxtnetwork.webviewjsbridge.model.ShareData;
import com.zxtnetwork.webviewjsbridge.model.WxPayData;
import com.zxtnetwork.webviewjsbridge.utils.Glide4Engine;
import com.zxtnetwork.webviewjsbridge.utils.MD5;
import com.zxtnetwork.webviewjsbridge.utils.MyUtils;
import com.zxtnetwork.webviewjsbridge.utils.ShareUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


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
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MyMultiplePermissionListener(activity, "需要权限，请开启。") {
                    @Override
                    public void authorizationSucceeded(MultiplePermissionsReport report) {
                        Matisse.from(activity)
                                .choose(MimeType.ofImage())
                                .capture(true)
                                .captureStrategy(
                                        new CaptureStrategy(true, "com.shunan.webviewjsbridge.fileProvider", "JsBridge"))
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

    @JavascriptInterface
    public void wxPay(String json) {
        WxPayData wxPayData = new Gson().fromJson(json, WxPayData.class);
        Constants.APP_ID = wxPayData.getAppId();
        PayReq req = new PayReq();
        req.appId = wxPayData.getAppId();
        req.partnerId = wxPayData.getMchId();
        req.prepayId = wxPayData.getPrepayId();
        req.packageValue = "Sign=WXPay";
        req.nonceStr = MD5.getMessageDigest(String.valueOf(new Random().nextInt(10000)).getBytes());
        req.timeStamp = String.valueOf(System.currentTimeMillis() / 1000);

        List<NameValuePair> signParams = new LinkedList<>();
        signParams.add(new NameValuePair("appid", req.appId));
        signParams.add(new NameValuePair("noncestr", req.nonceStr));
        signParams.add(new NameValuePair("package", req.packageValue));
        signParams.add(new NameValuePair("partnerid", req.partnerId));
        signParams.add(new NameValuePair("prepayid", req.prepayId));
        signParams.add(new NameValuePair("timestamp", req.timeStamp));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < signParams.size(); i++) {
            sb.append(signParams.get(i).getName());
            sb.append('=');
            sb.append(signParams.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(wxPayData.getApiKey());
        req.sign = MD5.getMessageDigest(sb.toString().getBytes());
        IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, null);
        msgApi.registerApp(wxPayData.getAppId());
        msgApi.sendReq(req);
    }
}
