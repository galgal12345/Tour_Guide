package com.drillgil.android.tourguide.Article;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.drillgil.android.tourguide.R;

public class ArticleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        //TODO: this activity need to be realeted to the ArticleFragment when the user will click on one of the recycle list items this activity will show up with more details
    }
}