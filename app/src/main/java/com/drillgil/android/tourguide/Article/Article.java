package com.drillgil.android.tourguide.Article;

public class Article {

    public String uid;
    public String article_title;
    public String article_image;
    public String article_content;
    public String time;
    public String date;

    public Article() {

    }

    public Article(String uid, String articleTitle, String article_image, String articleContent, String time, String date) {
        this.uid = uid;
        this.article_title = articleTitle;
        this.article_image = article_image;
        this.article_content = articleContent;
        this.time = time;
        this.date = date;
    }

    public String getArticleUid() {
        return uid;
    }
    public void setArticleUid(String uid) {
        this.uid = uid;
    }

    public String getArticleTitle() { return article_title; }
    public void setArticleTitle(String article_title) {
        this.article_title = article_title;
    }

    public String getArticleImage() { return article_image; }
    public void setArticleImage(String article_image) {
        this.article_image = article_image;
    }

    public String getArticleContent() { return article_content; }//TODO: we need it only for article detiles
    public void setArticleContent(String article_content) { this.article_content = article_content; }

    public String getArticleTime() {
        return time;
    }
    public void setArticleTime(String time) {
        this.time = time;
    }

    public String getArticleDate() {
        return date;
    }
    public void setArticleDate(String date) {
        this.date = date;
    }
}
