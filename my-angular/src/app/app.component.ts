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


  scanQrCode() {
    this.mService.scanQrCode();
  }
}
