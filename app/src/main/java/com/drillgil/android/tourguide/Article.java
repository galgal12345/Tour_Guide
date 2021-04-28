package com.drillgil.android.tourguide;

public class Article {

    private String mTitle;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Article(String mTitle, int imageResourceId) {
        this.mTitle = mTitle;
        this.imageResourceId = imageResourceId;
    }


    public String getTitle() {
        return mTitle;
    }


    public int getImageResourceId() {
        return imageResourceId;
    }


    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }


}
