package com.drillgil.android.tourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;


public class ArticleFragment extends Fragment {



    private RecyclerView mRecyclerView;
    private ArticleRecyclerViewAdapter mAdapter;

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




        mRecyclerView = rootView.findViewById(R.id.m_articles);
        mAdapter = new ArticleRecyclerViewAdapter(articleNames,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return rootView;
    }
}

