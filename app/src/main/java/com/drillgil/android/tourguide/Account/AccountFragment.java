package com.drillgil.android.tourguide.Account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.drillgil.android.tourguide.Article.ArticleDetailsActivity;
import com.drillgil.android.tourguide.Login.LoginActivity;
import com.drillgil.android.tourguide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    private ImageView logout;

    private LottieAnimationView userProfileImage;
    private TextView userProfName;

    private FirebaseAuth mAuth;
    private String currentUserId;
    private DatabaseReference profileUserRef, ArticlesRef;

    private TextView articlesNum, followersNum, followingNum;

    private CircleImageView accountListItemImg;
    private TextView accountListItemTitle, accountListItemSubtitle, accountListItemTime, accountListItemDate;


    private RecyclerView myAccountList;
    private FirebaseRecyclerOptions<Account> options;
    private FirebaseRecyclerAdapter<Account, MyAccountArticlesViewHolder> adapter;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        profileUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        ArticlesRef = FirebaseDatabase.getInstance().getReference().child("Articles").child(currentUserId);

        userProfileImage = (LottieAnimationView) rootView.findViewById(R.id.person_profile_pic);
        userProfName = (TextView) rootView.findViewById(R.id.person_full_name);

        articlesNum = rootView.findViewById(R.id.articles_num);
        followersNum = rootView.findViewById(R.id.followers_num);
        followingNum = rootView.findViewById(R.id.following_num);

        myAccountList = (RecyclerView) rootView.findViewById(R.id.m_recyclerview_your_public_articles);
        myAccountList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        myAccountList.setLayoutManager(linearLayoutManager);

        accountListItemImg = rootView.findViewById(R.id.account_list_img);
        accountListItemTitle = rootView.findViewById(R.id.account_list_title);
        accountListItemSubtitle = rootView.findViewById(R.id.account_list_subtitle);
        accountListItemTime = rootView.findViewById(R.id.account_list_time);
        accountListItemDate = rootView.findViewById(R.id.account_list_date);

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

//        DisplayMyAllArticlesIvWatchedRecently();

        DisplayMyAllMyPublicArticles();


        // Inflate the layout for this fragment
        return rootView;


    }

//    private void DisplayMyAllArticlesIvWatchedRecently() {
//
//
//    }


    //ToDo: we need to do something with the recycler view it dosent show on the main screen debug the problem and find out how you can deal with it
    private void DisplayMyAllMyPublicArticles() {

        Query SortArticlesInDecendingOrder = ArticlesRef.orderByChild("counter");

        options = new FirebaseRecyclerOptions.Builder<Account>().setQuery(SortArticlesInDecendingOrder, Account.class).build();
        adapter = new FirebaseRecyclerAdapter<Account, MyAccountArticlesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyAccountArticlesViewHolder holder, int position, @NonNull Account model) {

                final String ArticleKey = getRef(position).getKey();

                holder.setTitle(model.getTitle());
                holder.setImage(model.getImg());
                holder.setTime(model.getTime());
                holder.setDate(model.getDate());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickSheetIntent = new Intent(getActivity(), ArticleDetailsActivity.class);
                        clickSheetIntent.putExtra("ArticleKey", ArticleKey);
                        startActivity(clickSheetIntent);
                    }
                });
            }

            @NonNull
            @Override
            public MyAccountArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.acount_list_item, parent, false);
                return new MyAccountArticlesViewHolder(v);
            }
        };
        adapter.startListening();
        myAccountList.setAdapter(adapter);

    }

    /**
     * this class holds all the views on the MainActivity and sets up all values
     */
    public static class MyAccountArticlesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView articleTitle;
        ImageView articleImage;
        TextView articleTime;
        TextView articleDate;

        public MyAccountArticlesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mView = itemView;
            articleTitle = mView.findViewById(R.id.list_item_title);
            articleImage = mView.findViewById(R.id.list_item_img);
            //TODO: here we need to add location and time to present on the recyclerview
            articleTime = mView.findViewById(R.id.list_item_date);
            articleDate = mView.findViewById(R.id.list_item_time);
        }


        public void setTitle(String title) {
            articleTitle.setText(title);
        }

        public void setImage(String sheetimage) { Picasso.get().load(sheetimage).into(articleImage); }

        public void setTime(String time) {
            articleTime.setText("   " + time);
        }
        public void setDate(String date) { articleDate.setText("   " + date); }
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