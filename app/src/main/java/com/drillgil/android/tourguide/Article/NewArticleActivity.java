package com.drillgil.android.tourguide.Article;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.drillgil.android.tourguide.MainActivity2;
import com.drillgil.android.tourguide.OnBoarding.MainActivity;
import com.drillgil.android.tourguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class NewArticleActivity extends AppCompatActivity {

    private static final String TAG = "NewSheetActivity";


    private static final int Gallery_Pick = 1;
    private ProgressDialog loadingBar;

    private ImageView checkImg, goBackImg;
    private EditText articleTitle;
    private ImageView articleImg;
    private EditText articleContent;
    private ImageButton galleryBtn;

    private Uri ImageUri;

    private StorageReference ArticleImageRef;
    private DatabaseReference UserRef, ArticleRef;
    private FirebaseAuth mAuth;
    private long countArticles = 0;


    //TODO: maybe later we will add location
    private String Title,
            downloadUrl,
            Content,
            saveCurrentDate,
            saveCurrentTime,
            postRandomName,
            current_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);

        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        ArticleRef = FirebaseDatabase.getInstance().getReference().child("Articles");
        ArticleImageRef = FirebaseStorage.getInstance().getReference();

        checkImg = findViewById(R.id.publish_article);
        goBackImg = findViewById(R.id.go_back);

        articleTitle = findViewById(R.id.new_article_title);
        articleImg = findViewById(R.id.new_article_img);
        articleContent = findViewById(R.id.content);

        galleryBtn = findViewById(R.id.choose_from_gallery);

        loadingBar = new ProgressDialog(this);

        checkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ValidateSheetInfo();
            }
        });
        goBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToMainActivity();
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenGallery();

            }
        });


    }


    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {
            ImageUri = data.getData();
            articleImg.setImageURI(ImageUri);

        }
    }


    private void ValidateSheetInfo() {

        Content = articleContent.getText().toString();
        Title = articleTitle.getText().toString();

        if (TextUtils.isEmpty(Title)) {
            Toast.makeText(this, "Please write a article Title...", Toast.LENGTH_SHORT).show();
        } else if (ImageUri == null) {
            Toast.makeText(this, "Please select article image...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Content)) {
            Toast.makeText(this, "Please fill the article Content...", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Add New Article");
            loadingBar.setMessage("Please wait, while we are Updating your new article...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            StoringImageToFirebaseStorage();
        }
    }

    private void StoringImageToFirebaseStorage() {

        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calFordTime.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = ArticleImageRef.child("Sheet Images").child(ImageUri.getLastPathSegment() + postRandomName + ".jpg");


        filePath.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        downloadUrl = uri.toString();


                        filePath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {


                                if (task.isSuccessful()) {
                                    Toast.makeText(NewArticleActivity.this, "image upload successfully to storage", Toast.LENGTH_SHORT).show();

                                    SavingInformationIntoDatabase();
                                } else {
                                    String message = task.getException().getMessage();
                                    Toast.makeText(NewArticleActivity.this, "Error occured:" + message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void SavingInformationIntoDatabase() {

        ArticleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    countArticles = snapshot.getChildrenCount();
                } else {
                    countArticles = 0;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        UserRef.child(current_user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    HashMap postsMap = new HashMap();
                    postsMap.put("uid", current_user_id);
                    postsMap.put("article_title", Title);
                    postsMap.put("article_image", downloadUrl);
                    postsMap.put("article_content", Content);
                    postsMap.put("date", saveCurrentDate);
                    postsMap.put("time", saveCurrentTime);
                    postsMap.put("counter", countArticles);

                    ArticleRef.child(current_user_id + postRandomName).updateChildren(postsMap)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    if (task.isSuccessful()) {
                                        sendUserToMainActivity();
                                        Toast.makeText(NewArticleActivity.this, "New Article is updated successfully.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    } else {
                                        Toast.makeText(NewArticleActivity.this, "Error Occured while updating your article.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendUserToMainActivity() {

        Intent mainIntent = new Intent(NewArticleActivity.this, MainActivity2.class);
        startActivity(mainIntent);

    }

}






