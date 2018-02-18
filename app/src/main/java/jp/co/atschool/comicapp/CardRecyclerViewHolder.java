package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class CardRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageCard;
    public TextView titleCard;
    public TextView salesDateCard;

    public CardRecyclerViewHolder(View itemView) {
        super(itemView);
        imageCard = itemView.findViewById(R.id.imageCard);
        titleCard = itemView.findViewById(R.id.titleCard);
        salesDateCard = itemView.findViewById(R.id.salesDateCard);
    }

    public void bindViewHolder(Card card) {
        Picasso.with(imageCard.getContext()).load(card.getLargeImageUrl()git).into(imageCard);
       // imageCard.setImageResource(card.getLargeImageUrl());
        titleCard.setText(card.getTitle());
        salesDateCard.setText(card.getSalesDate());
    }
}
