package com.drillgil.android.tourguide;

public class Book {

    private String mTitle;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Book(String mTitle, int mImageResourceId) {
        this.mTitle = mTitle;
        this.mImageResourceId = mImageResourceId;
    }

    public String getDefaultTranslation() {
        return mTitle;
    }


    public int getmImageResourceId() {
        return mImageResourceId;
    }


    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


}
