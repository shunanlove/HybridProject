# BaseProject


/**
* 关闭当前界面
*/

colse() //关闭页面

/**
* 弹出Toast
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
