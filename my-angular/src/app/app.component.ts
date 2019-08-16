import { Component } from '@angular/core';
import { ZxtJsSdkService } from './zxt_js_sdk.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})


export class AppComponent {
  title = 'my-angular';
  constructor(private service: ZxtJsSdkService) {

  }

  ngOnInit() {
    console.log('应用已初始化');
  }

  close() {
    this.service.close();
  }

  nativeAlert() {
    this.service.nativeAlert('你好');
  }

  scanQrCode() {
    this.service.scanQrCode({
      onSuccess: (res: string) => {
        console.log('二维码：' + res);
      }
    });
  }

  goToBaiduMap() {
    let params = {
      "lat": "38.861318",
      "lng": "121.533141",
      "addressStr": "高新万达"
    }
    this.service.goToBaiduMap(params);
  }

  goToGaodeMap() {
    let params = {
      "lat": "38.861318",
      "lng": "121.533141",
      "addressStr": "高新万达"
    }
    this.service.goToGaodeMap(params);
  }

  goToTencentMap() {
    let params = {
      "lat": "38.861318",
      "lng": "121.533141",
      "addressStr": "高新万达"
    }
    this.service.goToTencentMap(params);
  }

  callPhone() {
    this.service.callPhone('13236903697');
  }

  pictureSelector() {
    this.service.pictureSelector({
      onSuccess: (res: any) => {
        for (let entry of res) {
          console.log('图片路径：' + entry);
        }
      }
    });
  }

  shareParameter() {
    let params = {
      "url": "http://s.caihuimall.net/eq-cms/course/courseDetail/1150934120092798976/1",
      "type": "1",
      "title": "测试分享",
      "titleUrl": "http://sharesdk.cn",
      "text": "我是分享文本",
      "imageUrl": "http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg"
    };
    this.service.shareParameter(params, {
      onSuccess: (res: string) => {
        switch (res) {
          case "0":
            console.log("分享成功");
            break;
          case "1":
            console.log("分享失败");
            break;
          case "2":
            console.log("分享关闭");
            break;
        }
      }
    });
  }

  wxPay() {
    let params = {
      "appId": "wx53baadf2f02263df",
      "mchId": "1492917752",
      "apiKey": "5acf3bfd512c4064b8ff95e1cec64e5f",
      "prepayId": "wx1517001600689238d428a05b1669158900"
    }
    this.service.wxPay(params, {
      onSuccess: (res: string) => {
        console.log(res);
      }
    });
  }

  AliPay() {
    let params = "app_id=2018010901719642&biz_content=%7B%22out_trade_no%22%3A%221160812710993203200%22%2C%22seller_id%22%3A%222088021477703852%22%2C%22total_amount%22%3A%221.01%22%2C%22subject%22%3A%22app%E6%94%AF%E4%BB%98%22%2C%22body%22%3A%22zyq%22%2C%22store_id%22%3A%22zyq%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fs.caihuimall.net%2Fpayment%2Fnotifyap&sign_type=RSA2&timestamp=2019-08-12%2015%3A18%3A16&version=1.0&sign=KDLHLo0zD4BQPkGQwrKQ4zC%2Bv4Esyozng23EtiLbxsWdHTLCBaYqr6P8t6CmQ1OpcSP8poye3OK4xBlDlx0bM%2FR8cU0v1oxbs3X5lVbhrSy1fLi0lbLC%2Ff4LoOsrvcOk%2FVurNff6CsC1IxNwSezxbx9WUoVlMmLbI8Yr0qAgdcFqyREHHtKy%2Byg6ndG6zM1odCCf%2FsoRHmClPFX5CiCg0hJcVKzdsZw%2BrWrPJDS4jCRq5AqI40hThX%2B2Ga8EsUV%2FJPJfnbabVq8DkF86cgBwHCSGVkBAxi3cLMWrMYE7GExYdv5ofE8RXlvZ8LbA6BjJCjSi1kH4CdFMdh92D2tPbg%3D%3D";
    this.service.AliPay(params, {
      onSuccess: (res: string) => {
        console.log(res);
      }
    });
  }

}
