package com.drillgil.android.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

class BookRecyclerViewAdapter extends ArrayAdapter<Book> {




    public BookRecyclerViewAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.txt_list_item1);
        miwokTextView.setText(currentBook.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_img);

        if (currentBook.hasImage()) {
            imageView.setImageResource(currentBook.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }


        return listItemView;
    }
}