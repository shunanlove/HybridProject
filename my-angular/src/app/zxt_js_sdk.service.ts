import { Injectable } from '@angular/core';
import ZxtJsSdk from "../lib/zxt-js-sdk";

@Injectable({
  providedIn: 'root'
})
export class ZxtJsSdkService {
  constructor() {
    console.log('###Init_Zxt_Js_Sdk_Service###');
  }
  close(): void {
    ZxtJsSdk.close();
  }

  nativeAlert(msg: string): void {
    ZxtJsSdk.nativeAlert(msg);
  }

  scanQrCode(callBack: any): void {
    ZxtJsSdk.scanQrCode(callBack);
  }

  goToBaiduMap(params: any): void {
    ZxtJsSdk.goToBaiduMap(params);
  }

  goToGaodeMap(params: any): void {
    ZxtJsSdk.goToGaodeMap(params);
  }

  goToTencentMap(params: any): void {
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
