import { Injectable } from '@angular/core';
import ZxtJsSdk from "../assets/lib/zxt-js-sdk";

@Injectable({
  providedIn: 'root'
})
export class ZxtJsSdkService {
  constructor() {
    console.log('###initService###');
  }
  close() {
    ZxtJsSdk.close();
  }

  nativeAlert(msg: string) {
    ZxtJsSdk.nativeAlert(msg);
  }

  scanQrCode() {
    ZxtJsSdk.scanQrCode({
      onSuccess: (res: string) => {
        console.log('二维码：' + res);
      }
    });

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

  goToBaiduMap(lat: string, lng: string, addressStr: string) {
    ZxtJsSdk.goToBaiduMap(lat, lng, addressStr);
  }

  goToGaodeMap(lat: string, lng: string, addressStr: string) {
    ZxtJsSdk.goToGaodeMap(lat, lng, addressStr);
  }

  goToTencentMap(lat: string, lng: string, addressStr: string) {
    ZxtJsSdk.goToTencentMap(lat, lng, addressStr);
  }

  callPhone(number: string) {
    ZxtJsSdk.callPhone(number);
  }

  pictureSelector() {
    ZxtJsSdk.pictureSelector({
      onSuccess: (res: any) => {
        for (let entry of res) {
          console.log('图片路径：' + entry);
        }
      }
    });
  }

  shareParameter() {
    let testData = {
      "url": "http://s.caihuimall.net/eq-cms/course/courseDetail/1150934120092798976/1",
      "type": "1",
      "title": "测试分享",
      "titleUrl": "http://sharesdk.cn",
      "text": "我是分享文本"
    };
    ZxtJsSdk.shareParameter(JSON.stringify(testData), {
      onSuccess: (res: string) => {
        console.log(res);
      }
    });
  }


}
