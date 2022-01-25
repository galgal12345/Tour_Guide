package com.drillgil.android.tourguide.Article;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drillgil.android.tourguide.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;


public class ArticleFragment extends Fragment {


    private static final String TAG = "MainActivity";
    private RecyclerView myArticlesList;
    private FirebaseRecyclerOptions<Article> options;
    private FirebaseRecyclerAdapter<Article, MyArticlesViewHolder> adapter;
    private FloatingActionButton newArticleBtn;

    private FirebaseAuth mAuth;
    private String currentUserID;
    private DatabaseReference ArticlesRef;



    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_book, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        ArticlesRef = FirebaseDatabase.getInstance().getReference().child("Articles");

        newArticleBtn = rootView.findViewById(R.id.fab);

        myArticlesList = (RecyclerView) rootView.findViewById(R.id.m_articles_recyclerview);
        myArticlesList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        myArticlesList.setLayoutManager(linearLayoutManager);



        newArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToNewSheetActivity();
            }
        });

        DisplayMyAllArticles();

        return rootView;
    }
    private void sendUserToNewSheetActivity() {

        Intent newSheetIntent = new Intent(getActivity(), NewArticleActivity.class);
        startActivity(newSheetIntent);

    }

    private void DisplayMyAllArticles() {

        Query SortArticlesInDecendingOrder = ArticlesRef.orderByChild("counter");

        options = new FirebaseRecyclerOptions.Builder<Article>().setQuery(SortArticlesInDecendingOrder, Article.class).build();
        adapter = new FirebaseRecyclerAdapter<Article, MyArticlesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull MyArticlesViewHolder holder, int position, @NonNull @NotNull Article model) {

                final String ArticleKey = getRef(position).getKey();

                holder.setTitle(model.getArticleTitle());
                holder.setImage(model.getArticleImage());
                holder.setTime(model.getArticleTime());
                holder.setDate(model.getArticleDate());



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
            public MyArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new MyArticlesViewHolder(v);
            }
        };
        adapter.startListening();
        myArticlesList.setAdapter(adapter);

    }

    /**
     * this class holds all the views on the MainActivity and sets up all values
     */
    public static class MyArticlesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView articleTitle;
        ImageView articleImage;
        TextView articleTime;
        TextView articleDate;

        public MyArticlesViewHolder(@NonNull @NotNull View itemView) {
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




}

