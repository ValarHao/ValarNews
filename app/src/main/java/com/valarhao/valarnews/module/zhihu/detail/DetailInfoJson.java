package com.valarhao.valarnews.module.zhihu.detail;

import java.util.List;

public class DetailInfoJson {

    /**
     * "body":"<div class="main-wrap content-wrap">...</div>"
     * "image_source":"ben.snider \/ CC BY"
     * "title":"橱柜五花八门的材质，业内人士是这样介绍的"
     * "image":"http:\/\/pic1.zhimg.com\/2e3355a1147b8b4aa04d67c084e99f50.jpg"
     * "share_url":"http:\/\/daily.zhihu.com\/story\/8842258"
     * "js":[]
     * "ga_prefix":"092920"
     * "images":["http:\/\/pic2.zhimg.com\/50f7d64cba9305ea756940615fca6935.jpg"]
     * "type":0
     * "id":8842258
     * "css":["http:\/\/news-at.zhihu.com\/css\/news_qa.auto.css?v=4b3e3"]}
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private List<String> js;
    private String ga_prefix;
    private int type;
    private int id;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public String getImageSource() {
        return image_source;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getShareUrl() {
        return share_url;
    }

    public List<String> getJs() {
        return js;
    }

    public String getGaPrefix() {
        return ga_prefix;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<String> getCss() {
        return css;
    }
}
