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


import java.text.MessageFormat;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";

    private TextView userProfName;
    private CircleImageView userProfileImage;

    private DatabaseReference ArticlesRef;

    private RecyclerView myAccountList, myRecentlyList;
    private FirebaseRecyclerOptions<Account> options;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //FIREBASE
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String currentUserId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        DatabaseReference profileUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        ArticlesRef = FirebaseDatabase.getInstance().getReference().child("Articles");


        userProfileImage = (CircleImageView) rootView.findViewById(R.id.person_profile_pic);
        userProfName = (TextView) rootView.findViewById(R.id.person_full_name);

        TextView articlesNum = rootView.findViewById(R.id.articles_num);
        TextView followersNum = rootView.findViewById(R.id.followers_num);
        TextView followingNum = rootView.findViewById(R.id.following_num);

        //RECYCLER_VIEW
        myAccountList = (RecyclerView) rootView.findViewById(R.id.m_recyclerview_your_public_articles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myAccountList.setLayoutManager(linearLayoutManager);

        //RECYCLER_VIEW
        myRecentlyList = (RecyclerView) rootView.findViewById(R.id.m_recyclerview_recently_articles_watched);
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myRecentlyList.setLayoutManager(myLinearLayoutManager);


        ImageView logout = rootView.findViewById(R.id.logout);


        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String myProfileImage = Objects.requireNonNull(snapshot.child("profileimage").getValue()).toString();
                    Picasso.get().load(myProfileImage).placeholder(R.drawable.profile).into(userProfileImage);

                    String myProfileName = Objects.requireNonNull(snapshot.child("fullname").getValue()).toString();
                    userProfName.setText(myProfileName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        logout.setOnClickListener(view -> logOut());
        DisplayMyAllMyWatchedRecentlyArticles();
        DisplayMyAllMyPublicArticles();

        return rootView;


    }


    private void DisplayMyAllMyWatchedRecentlyArticles() {
        Query SortArticlesInDescendingOrder = ArticlesRef.orderByChild("counter").limitToFirst(3);

        options = new FirebaseRecyclerOptions.Builder<Account>().setQuery(SortArticlesInDescendingOrder, Account.class).build();
        FirebaseRecyclerAdapter<Account, MyAccountArticlesViewHolder> adapter = new FirebaseRecyclerAdapter<Account, MyAccountArticlesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyAccountArticlesViewHolder holder, int position, @NonNull Account model) {

                final String ArticleKey = getRef(position).getKey();

                holder.setTitle(model.getArticle_title());
                holder.setImage(model.getArticle_image());
                holder.setTime(model.getTime());
                holder.setDate(model.getDate());

                holder.mView.setOnClickListener(v -> {
                    Intent clickSheetIntent = new Intent(getActivity(), ArticleDetailsActivity.class);
                    clickSheetIntent.putExtra("ArticleKey", ArticleKey);
                    startActivity(clickSheetIntent);
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
        myRecentlyList.setAdapter(adapter);
    }

    private void DisplayMyAllMyPublicArticles() {

        Query SortArticlesInDescendingOrder = ArticlesRef.orderByChild("counter").limitToFirst(3);

        options = new FirebaseRecyclerOptions.Builder<Account>().setQuery(SortArticlesInDescendingOrder, Account.class).build();
        FirebaseRecyclerAdapter<Account, MyAccountArticlesViewHolder> adapter = new FirebaseRecyclerAdapter<Account, MyAccountArticlesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyAccountArticlesViewHolder holder, int position, @NonNull Account model) {

                final String ArticleKey = getRef(position).getKey();

                holder.setTitle(model.getArticle_title());
                holder.setImage(model.getArticle_image());
                holder.setTime(model.getTime());
                holder.setDate(model.getDate());

                holder.mView.setOnClickListener(v -> {
                    Intent clickSheetIntent = new Intent(getActivity(), ArticleDetailsActivity.class);
                    clickSheetIntent.putExtra("ArticleKey", ArticleKey);
                    startActivity(clickSheetIntent);
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

    public static class MyAccountArticlesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView articleTitle;
        ImageView articleImage;
        TextView articleTime;
        TextView articleDate;

        public MyAccountArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            articleTitle = mView.findViewById(R.id.account_list_title);
            articleImage = mView.findViewById(R.id.account_list_img);
            articleTime = mView.findViewById(R.id.account_list_date);
            articleDate = mView.findViewById(R.id.account_list_time);
        }

        public void setTitle(String title) {
            articleTitle.setText(title);
        }
        public void setImage(String sheetImage) {
            Picasso.get().load(sheetImage).into(articleImage);
        }
        public void setTime(String time) {
            articleTime.setText(MessageFormat.format("   {0}", time));
        }
        public void setDate(String date) {
            articleDate.setText(MessageFormat.format("   {0}", date));
        }
    }

    private void logOut() {

        Context context = getActivity();
        assert context != null;
        SharedPreferences.Editor editor = context.getSharedPreferences("MEIR_P", Context.MODE_PRIVATE).edit();
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