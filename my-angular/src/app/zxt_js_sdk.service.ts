import { Injectable } from '@angular/core';
import ZxtJsSdk from "../lib/zxt-js-sdk";

@Injectable({
  providedIn: 'root'
})
export class ZxtJsSdkService {
  constructor() {
    console.log('###initService###');
  }
  close(): void {
    ZxtJsSdk.close();
  }

  nativeAlert(msg: string): void {
    ZxtJsSdk.nativeAlert(msg);
  }

  scanQrCode(callBack: any): void {
    ZxtJsSdk.scanQrCode(callBack);

    // ZxtJsSdk.ready({
    //   onSuccess: () => {

    //     ZxtJsSdk.scanQrCode({
    //       onSuccess: (res: string) => {
    //         console.log('二维码：' + res);
    //         ZxtJsSdk.nativeAlert('二维码：' + res);
    //       }
    //     });

    //   }
    // });
  }

  goToBaiduMap(params: string): void {
    ZxtJsSdk.goToBaiduMap(params);
  }

  goToGaodeMap(params: string): void {
    ZxtJsSdk.goToGaodeMap(params);
  }

  goToTencentMap(params: string): void {
    ZxtJsSdk.goToTencentMap(params);
  }

  callPhone(number: string): void {
    ZxtJsSdk.callPhone(number);
  }

  pictureSelector(callBack: any): void {
    ZxtJsSdk.pictureSelector(callBack);
  }

  shareParameter(params: any, callBack: any): void {
    ZxtJsSdk.shareParameter(params, callBack);
  }

  wxPay(params: any, callBack: any): void {
    ZxtJsSdk.wxPay(params, callBack);
  }

  AliPay(params: string, callBack: any): void {
    ZxtJsSdk.AliPay(params, callBack);
  }

}
