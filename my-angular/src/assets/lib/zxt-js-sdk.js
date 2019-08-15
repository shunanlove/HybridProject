let native = window.JsInterface;
let mScanQrCode_CallBack;
window['scanQrCode_CallBack'] = function (res) {
    mScanQrCode_CallBack.onSuccess(res);
}

function zxtJsSdk() {

    let ready = function (callBack) {
        if (native)
            callBack.onSuccess();
    };

    let close = function () {
        if (native)
            native.close();
    };

    let nativeAlert = function (msg) {
        if (native)
            native.nativeAlert(msg);
    };

    let scanQrCode = function (scanQrCode_CallBack) {
        if (native) {
            mScanQrCode_CallBack = scanQrCode_CallBack;
            native.scanQrCode();
        }
    };

    return {
        ready: ready,
        close: close,
        nativeAlert: nativeAlert,
        scanQrCode: scanQrCode
    };
}

export default {

    ready: zxtJsSdk().ready,
    close: zxtJsSdk().close,
    nativeAlert: zxtJsSdk().nativeAlert,
    scanQrCode: zxtJsSdk().scanQrCode

}