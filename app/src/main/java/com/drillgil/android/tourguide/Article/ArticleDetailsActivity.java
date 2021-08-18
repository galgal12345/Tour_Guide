package com.drillgil.android.tourguide.Article;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.drillgil.android.tourguide.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ArticleDetailsActivity extends AppCompatActivity {

    private ImageView clickImageView;

    private TextView clickTitleTextView,
            clickDateTextView,
            clickTimeTextView,
            clickContentTextView;

    private DatabaseReference ClickArticleRef;

    private String ArticleKey,
            image,
            title,
            date,
            time,
            content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        ArticleKey = getIntent().getExtras().get("ArticleKey").toString();
        ClickArticleRef = FirebaseDatabase.getInstance().getReference().child("Articles").child(ArticleKey);


        clickImageView =  findViewById(R.id.click_image_view);
        clickTitleTextView = findViewById(R.id.click_title_view);
        clickDateTextView = findViewById(R.id.click_date_view);
        clickTimeTextView = findViewById(R.id.click_time_view);
        clickContentTextView =  findViewById(R.id.click_content_view);

        ClickArticleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    image = snapshot.child("article_image").getValue().toString();
                    title = snapshot.child("article_title").getValue().toString();

                    date = snapshot.child("date").getValue().toString();
                    time = snapshot.child("time").getValue().toString();
                    content = snapshot.child("article_content").getValue().toString();

                    Picasso.get().load(image).placeholder(R.drawable.ic_launcher_foreground).into(clickImageView);
                    clickTitleTextView.setText(title);
                    clickDateTextView.setText(date);
                    clickTimeTextView.setText(time);
                    clickContentTextView.setText(content);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}