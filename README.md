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
 
 /**
 * 支付宝支付
 * @param url
 */
