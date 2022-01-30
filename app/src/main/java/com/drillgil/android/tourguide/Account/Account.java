package com.drillgil.android.tourguide.Account;

public class Account {

    public String uid;
    public String article_title;
    public String article_image;
    public String article_content;
    public String time;
    public String date;

    public Account() {}

    public Account(String uid, String articleTitle, String article_image, String articleContent, String time, String date) {
        this.uid = uid;
        this.article_title = articleTitle;
        this.article_image = article_image;
        this.article_content = articleContent;
        this.time = time;
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public String getArticle_title() {
        return article_title;
    }

    public String getArticle_image() {
        return article_image;
    }

    public String getArticle_content() {
        return article_content;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}
