import { Injectable } from '@angular/core';
import ZxtJsSdk from "../assets/lib/zxt-js-sdk";

@Injectable({
  providedIn: 'root'
})
export class ZxtJsSdkService {
  constructor() {
    console.log('###initService###');
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
}
