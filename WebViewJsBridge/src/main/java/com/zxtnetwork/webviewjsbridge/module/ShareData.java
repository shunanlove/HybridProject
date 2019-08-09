package com.zxtnetwork.webviewjsbridge.module;

/**
 * @Description: java类作用描述
 * @Author: 大魔王老杨
 * @Date: 2019-08-01 11:29
 */
public class ShareData {
    private String type;
    private String title;
    private String titleUrl;
    private String text;
    private String  url;

    public ShareData(String type, String title, String titleUrl, String text, String url) {
        this.type = type;
        this.title = title;
        this.titleUrl = titleUrl;
        this.text = text;
        this.url = url;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
