package com.valarhao.valarnews.module.main;

public class NewsItem {

    private String mImgLink;
    private String mTitle;

    public void setImgLink(String imgLink) {
        mImgLink = imgLink;
    }

    public String getImgLink() {
        return mImgLink;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public String toString() {
        return "NewsItem [imgLink=" + mImgLink + ", title=" + mTitle + "]";
    }
}
