package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewHolder> {
   // private Cards cards;
    private ArrayList<Card> cardList;
    private static final int VIEW_ITEM_TYPE = 0;

    public void setList(ArrayList<Card> list) {
        cardList = list;
    }

//    public CardRecyclerViewAdapter(Cards cards) {
//        this.cards = cards;
//    }

    @Override
    public int getItemViewType(int position) {
        Object item = cardList.get(position);
        if (item instanceof Cards) {
            return VIEW_ITEM_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public CardRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_ITEM_TYPE:
                View itemView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
                return new CardRecyclerViewHolder(itemView);
        }
        return null;
    }

//    @Override
//    public void onBindViewHolder(CardRecyclerViewHolder holder, int position) {
//        //  holder.imageCard.setImageResource(cards.get(position).getLargeImageUrl());
//        holder.titleCard.setText(cards.getCards().get(position).getTitle());
//        holder.salesDateCard.setText(cards.getCards().get(position).getSalesDate());
//        Timber.d("aaaaaaa");
//    }

    @Override
    public void onBindViewHolder(CardRecyclerViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_ITEM_TYPE:
                CardRecyclerViewHolder cardRecyclerViewHolder = (CardRecyclerViewHolder) holder;
                Card card = (Card) cardList.get(position);
                cardRecyclerViewHolder.bindViewHolder(card);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (cardList != null) {
            return cardList.size();
        }
        return 0;
    }
}
