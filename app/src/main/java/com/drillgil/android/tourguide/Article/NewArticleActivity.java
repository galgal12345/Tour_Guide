package com.drillgil.android.tourguide.Article;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.drillgil.android.tourguide.Article.ArticleViewModel;
import com.drillgil.android.tourguide.R;
import com.drillgil.android.tourguide.databinding.ActivityNewArticleBinding;

import java.util.Arrays;
import java.util.List;

public class NewArticleActivity extends AppCompatActivity {
    private static final String TAG = "SelectImageActivity";

    private static final int REQUEST_CODE_IMAGE = 100;
    private static final int REQUEST_CODE_PERMISSIONS = 101;

    private static final String KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT";
    private static final int MAX_NUMBER_REQUEST_PERMISSIONS = 2;

    public static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";


    private static final List<String> sPermissions = Arrays.asList(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    );

    private int mPermissionRequestCount;
    private ActivityNewArticleBinding binding;

    private ImageButton chooseImageFromGallery;
    final static int Gallery_Pick = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);

          chooseImageFromGallery = findViewById(R.id.choose_from_gallery);

        if (savedInstanceState != null) {
            mPermissionRequestCount =
                    savedInstanceState.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0);
        }

        //send the user to the mobile phone gallery
        chooseImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);

            }
        });
    }

//    /** Save the permission request count on a rotate **/
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt(KEY_PERMISSIONS_REQUEST_COUNT, mPermissionRequestCount);
//    }
//
//    /**
//     * Request permissions twice - if the user denies twice then show a toast about how to update
//     * the permission for storage. Also disable the button if we don't have access to pictures on
//     * the device.
//     */
//    private void requestPermissionsIfNecessary() {
//        if (!checkAllPermissions()) {
//            if (mPermissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
//                mPermissionRequestCount += 1;
//                ActivityCompat.requestPermissions(
//                        this,
//                        sPermissions.toArray(new String[0]),
//                        REQUEST_CODE_PERMISSIONS);
//            } else {
//                Toast.makeText(this, R.string.set_permissions_in_settings,
//                        Toast.LENGTH_LONG).show();
//                binding.chooseFromGallery.setEnabled(false);
//            }
//        }
//    }
//
//    private boolean checkAllPermissions() {
//        boolean hasPermissions = true;
//        for (String permission : sPermissions) {
//            hasPermissions &= ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
//        }
//        return hasPermissions;
//    }
//
//    /** Permission Checking **/
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            requestPermissionsIfNecessary(); // no-op if permissions are granted already.
//        }
//    }

    /** Image Selection **/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_IMAGE:
                    handleImageRequestResult(data);
                    break;
                default:
                    Log.d(TAG, "Unknown request code.");
            }
        } else {
            Log.e(TAG, String.format("Unexpected Result code %s", resultCode));
        }
    }

    private void handleImageRequestResult(Intent data) {
        ArticleViewModel model = new ViewModelProvider(this).get(ArticleViewModel.class);

        // Image uri should be stored in the ViewModel; put it there then display
        Intent intent = getIntent();
        String imageUriExtra = intent.getStringExtra(KEY_IMAGE_URI);
        model.setImageUri(imageUriExtra);

        if (model.getImageUri() != null) {
            Glide.with(this).load(model.getImageUri()).into(binding.newArticleImg);
        }
    }


    }






