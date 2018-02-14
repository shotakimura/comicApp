package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import timber.log.Timber;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private Cards cards;

    public CardRecyclerViewAdapter(Cards cards) {
        this.cards = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent,false);
        CardViewHolder vh = new CardViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        //  holder.imageCard.setImageResource(cards.get(position).getLargeImageUrl());
        holder.titleCard.setText(cards.getCards().get(position).getTitle());
        holder.salesDateCard.setText(cards.getCards().get(position).getSalesDate());
        Timber.d("aaaaaaa");
    }

    @Override
    public int getItemCount() {
        return cards.getCards().size();
    }
}
