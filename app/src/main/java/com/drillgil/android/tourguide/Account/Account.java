package com.drillgil.android.tourguide.Account;

public class Account {

    public String img;
    public String title;
    public String time;
    public String date;

    public Account(String img, String title,  String time, String date) {
        this.img = img;
        this.title = title;
        this.time = time;
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
