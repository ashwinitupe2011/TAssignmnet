package com.example.windows10.tassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.windows10.tassignment.R;
import com.example.windows10.tassignment.data.Article;
import com.example.windows10.tassignment.viewHolder.ItemViewHolder;

/**
 *         Created by ashwini on 11/03/18.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Context mContext;
    private List<Article> mDatalist = new ArrayList<Article>();
    DateFormat dateFormat;

    public ArticleAdapter(Context mContext, List<Article> list) {
        this.mContext = mContext;
        this.mDatalist = list;
        dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Article article = mDatalist.get(position);

        if (article.getArticleTitle()!= null)
        holder.tvTitle.setText(article.getArticleTitle());

        if (article.getArticleDescription()!= null)
        holder.tvDescription.setText(article.getArticleDescription());

        if(article.getUserURL() != null)
        Glide.with(mContext).load(article.getUserURL()).into(holder.imUserImage);

        else
            Glide.with(mContext).load(R.mipmap.ic_launcher).into(holder.imUserImage);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Item Clicked",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }
}
