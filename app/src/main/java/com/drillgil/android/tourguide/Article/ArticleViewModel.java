package com.drillgil.android.tourguide.Article;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class ArticleViewModel extends AndroidViewModel {

    private Uri mImageUri;


    public ArticleViewModel(@NonNull Application application) {
        super(application);
    }
    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    void applyBlur(int blurLevel) {

    }

    private Uri uriOrNull(String uriString) {
        if (!TextUtils.isEmpty(uriString)) {
            return Uri.parse(uriString);
        }
        return null;
    }

    void setImageUri(String uri) {
        mImageUri = uriOrNull(uri);
    }

    Uri getImageUri() {
        return mImageUri;
    }
}
