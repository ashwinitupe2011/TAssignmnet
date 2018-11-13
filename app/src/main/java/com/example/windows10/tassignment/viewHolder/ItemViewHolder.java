package com.example.windows10.tassignment.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows10.tassignment.R;

/**
 * Created by amit on 3/23/18.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public  TextView tvDescription;
    public ImageView imUserImage;
    public CardView mCardView;


    public ItemViewHolder(View itemView) {
        super(itemView);
        tvTitle =  itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        imUserImage = itemView.findViewById(R.id.imArticleImage);
        mCardView = itemView.findViewById(R.id.cardView);
    }
}
