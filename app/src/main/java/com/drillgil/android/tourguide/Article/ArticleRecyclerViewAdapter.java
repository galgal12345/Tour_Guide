package com.drillgil.android.tourguide.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drillgil.android.tourguide.R;

import java.util.ArrayList;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ArticleViewHolder> {

    private final ArrayList<Article> mArticleList;
    private LayoutInflater mInflater;

    public ArticleRecyclerViewAdapter(ArrayList<Article> mArticleList, Context context) {
        mInflater = LayoutInflater.from(context);
        this.mArticleList = mArticleList;
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {
       public final TextView wordItemView;
       public final ImageView imgItemView;
       final ArticleRecyclerViewAdapter mAdapter;



       public ArticleViewHolder(@NonNull View itemView, ArticleRecyclerViewAdapter adapter) {
           super(itemView);
           wordItemView = itemView.findViewById(R.id.txt_list_item1);
           imgItemView = itemView.findViewById(R.id.list_item_img);
           this.mAdapter = adapter;
       }
   }

    @NonNull
    @Override
    public ArticleRecyclerViewAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new ArticleViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleRecyclerViewAdapter.ArticleViewHolder holder, int position) {
        Article article= mArticleList.get(position);
        holder.wordItemView.setText(article.getTitle());
        holder.imgItemView.setImageResource(article.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}