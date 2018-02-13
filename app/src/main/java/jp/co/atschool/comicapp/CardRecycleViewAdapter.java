package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class CardRecycleViewAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private List<Card> cards;

    public CardRecycleViewAdapter(List<Card> cards) {
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
        holder.titleCard.setText(cards.get(position).getTitle());
        holder.salesDateCard.setText(cards.get(position).getSalesDate());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
