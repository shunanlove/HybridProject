# BaseProject


/**
* 关闭当前界面
*/
close()

/**
* 弹出Toast
*
* @param mes //提示文字内容
*/
nativeAlert(String mes)

/**
* 跳转扫码页面
*/
scanQrCode()

/**
* 跳转百度地图
*
* @param mLat        //经度
* @param mLng        //维度
* @param mAddressStr //目的地
*/
goToBaiduMap(String mLat, String mLng, String mAddressStr)

/**
* 跳转高德地图
*
* @param mLat        //经度
* @param mLng        //维度
* @param mAddressStr //目的地
*/
goToGaodeMap(String mLat, String mLng, String mAddressStr)

/**
* 跳转腾讯地图
*
* @param mLat        //经度
* @param mLng        //维度
* @param mAddressStr //目的地
*/
goToTencentMap(String mLat, String mLng, String mAddressStr)

/**
* 拨打电话（跳转到拨号界面，用户手动点击拨打）
*
* @param phoneNum 电话号码
*/
callPhone(String phoneNum)

/**
 * 分享以及集成步骤
 *(1)在根目录gradle中加入 dependencies {
        classpath "com.mob.sdk:MobSDK:2018.0319.1724"
    }
 * (2)在library的gradle中引入:apply from: '../MobSDK.gradle'
 * (3)在app的gradle中引入:apply plugin: 'com.mob.sdk'
 * (4)新建mobSdk.gradle;
  shareParameter(String shareJson)
 代码如下:
 apply plugin: 'com.mob.sdk'
 MobSDK {
 //配置mob平台信息
    appKey "您的appKey xxxx"
    appSecret "您的appSecret xxxx"
    ShareSDK {
        loopShare true
        devInfo {
        //配置平台信息;如微信,QQ,微博等...
            Wechat {
                appId "wx53baadf2f02263df"
                appSecret "1c17b0f2549cf8a191dd9a0519f999ea"
            }

        }
    }
}
*(5)在manifest中配置MobUIShell,如下:
 <activity
            android:name="com.mob.tools.MobUIShell"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:theme="@android:style/Theme.Translucent.NoTitleBar"
            android:windowSoftInputMode="stateHidden|adjustResize">
        </activity>
 * @param shareJson
 */
 shareParameter(String shareJson)

#### 支付宝支付
- @param url
 AliPay(String url)
 集成步骤:
 1.将 alipaySdk-15.5.7-20181023110917.aar 包放在您的应用工程的 libs 目录下
 2.在主项目的 build.gradle 中，添加下面的内容，将 libs 目录作为依赖仓库：
 allprojects {
     repositories {

         // 添加下面的内容
         flatDir {
             dirs 'libs'
         }

         // ... jcenter() 等其他仓库
     }
 }
 3.在您 App Module 的 build.gradle 中，添加下面的内容，将支付宝 SDK 作为项目依赖：
 dependencies {

     // 添加下面的内容
     compile (name: 'alipaySdk-15.5.7-20181023110917', ext: 'aar')

     // ... 其他依赖项
 }
 4.支付接口调用
 final String orderInfo = info;   // 订单信息

 		Runnable payRunnable = new Runnable() {

 			@Override
 			public void run() {
 				PayTask alipay = new PayTask(DemoActivity.this);
                Map <String,String> result = alipay.payV2(orderInfo,true);

 				Message msg = new Message();
 				msg.what = SDK_PAY_FLAG;
 				msg.obj = result;
 				mHandler.sendMessage(msg);
 			}
 		};
 	     // 必须异步调用
 		Thread payThread = new Thread(payRunnable);
 		payThread.start();
 5.支付结果获取和处理
 private Handler mHandler = new Handler() {
 		public void handleMessage(Message msg) {
 			Result result = new Result((String) msg.obj);
 			Toast.makeText(DemoActivity.this, result.getResult(),
 						Toast.LENGTH_LONG).show();
 		};
 	};

