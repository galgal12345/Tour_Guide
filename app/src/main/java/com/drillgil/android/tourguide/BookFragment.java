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


public class BookFragment extends Fragment {

    BookRecyclerViewAdapter adapter;


    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_book, container, false);


        // data to populate the RecyclerView with
        ArrayList<Book> bookNames = new ArrayList<>();
        bookNames.add(new Book("3 Wines Tourism Breaks in Europe",R.drawable.europe_paris));
        bookNames.add(new Book("Norway: stay in a spectacular treetop cabin amid in Norwegian countryside",R.drawable.norway_lake));
        bookNames.add(new Book("3 UK Historic House Hotels with gorgeous gardens",R.drawable.uk_houses));
        bookNames.add(new Book("Which of the Cyclades Islands in Greece should you visit?",R.drawable.greece_crete));
        bookNames.add(new Book("Top 10 places to see in Puglia, Italy",R.drawable.italy_lake));




        // set up the RecyclerView
        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(getActivity(), bookNames);
        ListView listView = (ListView) rootView.findViewById(R.id.m_books);
        listView.setAdapter(adapter);



        return rootView;
    }
}

