let native = window.JsInterface;

let mScanQrCode_callBack;
let mPictureSelector_callBack;
let mShareParameter_callBack;

window['scanQrCode_callBack'] = function (res) {
    mScanQrCode_callBack.onSuccess(res);
}

window['pictureSelector_callBack'] = function (res) {
    mPictureSelector_callBack.onSuccess(JSON.parse(res));
}

window['shareParameter_callBack'] = function (res) {
    mShareParameter_callBack.onSuccess(res);
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

    let goToBaiduMap = function (lat, lng, addressStr) {
        if (native) {
            native.goToBaiduMap(lat, lng, addressStr);
        }
    };

    let goToGaodeMap = function (lat, lng, addressStr) {
        if (native) {
            native.goToGaodeMap(lat, lng, addressStr);
        }
    };

    let goToTencentMap = function (lat, lng, addressStr) {
        if (native) {
            native.goToTencentMap(lat, lng, addressStr);
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

    let shareParameter = function (data, callBack) {
        if (native) {
            mShareParameter_callBack = callBack;
            native.shareParameter(data);
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
        shareParameter: shareParameter
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
    shareParameter: zxtJsSdk().shareParameter

}