package com.shunan.webviewjsbridge.utils;

import android.content.Context;

import com.shunan.webviewjsbridge.R;
import com.shunan.webviewjsbridge.module.ShareJson;

import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * @Description: java类作用描述
 * @Author: 大魔王老杨
 * @Date: 2019-08-01 11:19
 */
public class ShareUtils {

    public static void showShare(Context context, ShareJson json) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(json.getTitle());
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(json.getTitleUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(json.getText());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(json.getUrl());
        // 启动分享GUI
        oks.show(context);
    }
}
