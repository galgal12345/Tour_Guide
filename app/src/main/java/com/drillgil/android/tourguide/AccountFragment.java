package com.drillgil.android.tourguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.drillgil.android.tourguide.Home.HomeFragment;
import com.drillgil.android.tourguide.Login.LoginActivity;
import com.drillgil.android.tourguide.OnBoarding.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";


    private ImageView logout;

    private LottieAnimationView userProfileImage;
    private TextView userProfName;

    private FirebaseAuth mAuth;
    private String currentUserId;
    private DatabaseReference profileUserRef;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        profileUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

        userProfileImage = (LottieAnimationView) rootView.findViewById(R.id.person_profile_pic);
        userProfName = (TextView) rootView.findViewById(R.id.person_full_name);


        logout = rootView.findViewById(R.id.logout);

        //sets all the user info
        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String myProfileImage = snapshot.child("profileimage").getValue().toString();
                    Picasso.get().load(myProfileImage).placeholder(R.drawable.profile).into(userProfileImage);

                    String myProfileName = snapshot.child("fullname").getValue().toString();
                    userProfName.setText(myProfileName);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logOut();
            }
        });


        // Inflate the layout for this fragment
        return rootView;


    }

    private void logOut() {

        Context context = getActivity();
        SharedPreferences.Editor editor = context.getSharedPreferences("MEIR_P", context.MODE_PRIVATE).edit();
        editor.putString("is_logged_in", "no");
        editor.apply();
        Log.d(TAG, "logout");
        SendUserToLoginActivity();
    }

    private void SendUserToLoginActivity() {
        Log.d(TAG, "SendUserToLoginActivity: ");
        Intent loginIntent = new Intent(this.getActivity(), LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//🏁 🏁 🏁 for validations
        startActivity(loginIntent);

    }
}