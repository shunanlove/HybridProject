package com.zxtnetwork.webviewjsbridge.event;

public class MessageEvent {
    private int respErrCode;

    public MessageEvent(int respErrCode) {
        this.respErrCode = respErrCode;
    }

    public int getRespErrCode() {
        return respErrCode;
    }
}
