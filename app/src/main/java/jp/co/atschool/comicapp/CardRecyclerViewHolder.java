package jp.co.atschool.comicapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class CardRecyclerViewHolder extends RecyclerView.ViewHolder {

    private CardView viewCard;

    private ImageView imageCard;
    private TextView titleCard;
    private TextView salesDateCard;

    public CardRecyclerViewHolder(View itemView) {
        super(itemView);

        viewCard = itemView.findViewById(R.id.viewCard);

        imageCard = itemView.findViewById(R.id.imageCard);
        titleCard = itemView.findViewById(R.id.titleCard);
        salesDateCard = itemView.findViewById(R.id.salesDateCard);
    }

    public void bindViewHolder(Card card) {
        Picasso.with(imageCard.getContext()).load(card.getLargeImageUrl()).into(imageCard);
       // imageCard.setImageResource(card.getLargeImageUrl());
       // titleCard.setText(card.getTitle());
        salesDateCard.setText(card.getSalesDate());

        final String title = card.getTitle();
        String saveTitle;
        saveTitle = title.replace("（", " ");
        saveTitle = saveTitle.replace("）", "");
        titleCard.setText(saveTitle);

//        viewCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }

    public CardView getViewCard() {
        return viewCard;
    }

}
