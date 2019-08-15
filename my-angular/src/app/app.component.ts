import { Component } from '@angular/core';
import { ZxtJsSdkService } from './zxt_js_sdk.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})


export class AppComponent {
  title = 'my-angular';
  private mService: any;
  constructor(private service: ZxtJsSdkService) {

    this.mService = service;

  }

  close() {
    this.mService.close();
  }

  nativeAlert(msg: string) {
    this.mService.nativeAlert('你好');
  }

  scanQrCode() {
    this.mService.scanQrCode();
  }

  goToBaiduMap(lat: string, lng: string, addressStr: string) {
    this.mService.goToBaiduMap(lat, lng, addressStr);
  }

  goToGaodeMap(lat: string, lng: string, addressStr: string) {
    this.mService.goToGaodeMap(lat, lng, addressStr);
  }

  goToTencentMap(lat: string, lng: string, addressStr: string) {
    this.mService.goToTencentMap(lat, lng, addressStr);
  }

  callPhone(number: string) {
    this.mService.callPhone(number);
  }

  pictureSelector() {
    this.mService.pictureSelector();
  }

  shareParameter() {
    this.mService.shareParameter();
  }

}
