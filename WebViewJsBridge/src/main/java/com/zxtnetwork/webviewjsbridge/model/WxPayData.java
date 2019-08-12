package com.zxtnetwork.webviewjsbridge.model;
//{"appid":"wxb4ba3c02aa476ea1","partnerid":"1900006771","package":"Sign=WXPay","noncestr":"e5326e5ddda86ffa9950c34c7d6283d2","timestamp":1565581291,"prepayid":"wx12114131560278fa276d35bb1647623532","sign":"B7B2242FBDDDBA75144761FC5029AE9A"}
public class WxPayData {

    private String appId;
    private String mchId;
    private String apiKey;
    private String prepayId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
