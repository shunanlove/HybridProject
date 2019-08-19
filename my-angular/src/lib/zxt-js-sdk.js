let native = window.JsInterface;

let mScanQrCode_callBack;
let mPictureSelector_callBack;
let mShareParameter_callBack;
let mWxPay_callBack;
let mAliPay_callBack;

window['scanQrCode_callBack'] = function (res) {
    mScanQrCode_callBack.onSuccess(res);
}

window['pictureSelector_callBack'] = function (res) {
    mPictureSelector_callBack.onSuccess(JSON.parse(res));
}

window['shareParameter_callBack'] = function (res) {
    mShareParameter_callBack.onSuccess(res);
}

window['wxPay_callBack'] = function (res) {
    mWxPay_callBack.onSuccess(res);
}

window['aliPay_callBack'] = function (res) {
    mAliPay_callBack.onSuccess(res);
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

    let scanQrCode = function (callBack) {
        if (native) {
            mScanQrCode_callBack = callBack;
            native.scanQrCode();
        }
    };

    let goToBaiduMap = function (params) {
        if (native) {
            native.goToBaiduMap(JSON.stringify(params));
        }
    };

    let goToGaodeMap = function (params) {
        if (native) {
            native.goToGaodeMap(JSON.stringify(params));
        }
    };

    let goToTencentMap = function (params) {
        if (native) {
            native.goToTencentMap(JSON.stringify(params));
        }
    };

    let callPhone = function (number) {
        if (native) {
            native.callPhone(number);
        }
    };

    let pictureSelector = function (callBack) {
        if (native) {
            mPictureSelector_callBack = callBack;
            native.pictureSelector();
        }
    };

    let shareParameter = function (params, callBack) {
        if (native) {
            mShareParameter_callBack = callBack;
            native.shareParameter(JSON.stringify(params));
        }
    };

    let wxPay = function (params, callBack) {
        if (native) {
            mWxPay_callBack = callBack;
            native.wxPay(JSON.stringify(params));
        }
    };

    let AliPay = function (params, callBack) {
        if (native) {
            mAliPay_callBack = callBack;
            native.AliPay(params);
        }
    };

    return {
        ready: ready,
        close: close,
        nativeAlert: nativeAlert,
        scanQrCode: scanQrCode,
        goToBaiduMap: goToBaiduMap,
        goToGaodeMap: goToGaodeMap,
        goToTencentMap: goToTencentMap,
        callPhone: callPhone,
        pictureSelector: pictureSelector,
        shareParameter: shareParameter,
        wxPay: wxPay,
        AliPay: AliPay
    };
}

export default {

    ready: zxtJsSdk().ready,
    close: zxtJsSdk().close,
    nativeAlert: zxtJsSdk().nativeAlert,
    scanQrCode: zxtJsSdk().scanQrCode,
    goToBaiduMap: zxtJsSdk().goToBaiduMap,
    goToGaodeMap: zxtJsSdk().goToGaodeMap,
    goToTencentMap: zxtJsSdk().goToTencentMap,
    callPhone: zxtJsSdk().callPhone,
    pictureSelector: zxtJsSdk().pictureSelector,
    shareParameter: zxtJsSdk().shareParameter,
    wxPay: zxtJsSdk().wxPay,
    AliPay: zxtJsSdk().AliPay

}