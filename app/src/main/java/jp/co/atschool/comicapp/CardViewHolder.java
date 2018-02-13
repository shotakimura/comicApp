package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageCard;
    public TextView titleCard;
    public TextView salesDateCard;
    public CardViewHolder(View itemView) {
        super(itemView);
        imageCard = itemView.findViewById(R.id.imageCard);
        titleCard = itemView.findViewById(R.id.titleCard);
        salesDateCard = itemView.findViewById(R.id.salesDateCard);
    }
}
