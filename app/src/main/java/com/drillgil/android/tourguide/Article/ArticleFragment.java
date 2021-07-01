package com.drillgil.android.tourguide.Article;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drillgil.android.tourguide.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ArticleFragment extends Fragment {


    private static final String TAG =  "ArticleFragment";
    private RecyclerView mRecyclerView;
    private ArticleRecyclerViewAdapter mAdapter;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;


    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_book, container, false);

        // data to populate the RecyclerView with
        ArrayList<Article> articleNames = new ArrayList<>();
        articleNames.add(new Article("3 Wines Tourism Breaks in Europe",R.drawable.europe_paris));
        articleNames.add(new Article("Norway: stay in a spectacular treetop cabin amid in Norwegian countryside",R.drawable.norway_lake));
        articleNames.add(new Article("3 UK Historic House Hotels with gorgeous gardens",R.drawable.uk_houses));
        articleNames.add(new Article("Which of the Cyclades Islands in Greece should you visit?",R.drawable.greece_crete));
        articleNames.add(new Article("Top 10 places to see in Puglia, Italy",R.drawable.italy_lake));

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), NewArticleActivity.class );
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });


        mRecyclerView = rootView.findViewById(R.id.m_articles);
        mAdapter = new ArticleRecyclerViewAdapter(articleNames,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));






        return rootView;
    }



}

