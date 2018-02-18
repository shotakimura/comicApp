package jp.co.atschool.comicapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by shotakimura on 2018/02/17.
 */

public class CardsRecyclerViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView cardsRecyclerView;
    private CardRecyclerViewAdapter cardRecyclerViewAdapter;

    public CardsRecyclerViewHolder(View itemView) {
        super(itemView);

        // RecyclerView
        cardsRecyclerView = itemView.findViewById(R.id.recyclerComics);

        // LayoutManager(Horizontal)
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        cardsRecyclerView.setLayoutManager(linearLayoutManager);

        // Adapter
        cardRecyclerViewAdapter = new CardRecyclerViewAdapter();
        cardsRecyclerView.setAdapter(cardRecyclerViewAdapter);
    }

    public void bindViewHolder(Cards cards) {
        // データをセット
        cardRecyclerViewAdapter.setList((ArrayList) cards.getCards());
        cardRecyclerViewAdapter.notifyDataSetChanged();
    }
}
