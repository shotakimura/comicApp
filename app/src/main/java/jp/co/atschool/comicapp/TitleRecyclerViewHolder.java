package jp.co.atschool.comicapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shotakimura on 2018/02/17.
 */

public class TitleRecyclerViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView cardsRecyclerView;
    private CardRecyclerViewAdapter cardRecyclerViewAdapter;

    public TextView titleComic;


    public TitleRecyclerViewHolder(View itemView) {
        super(itemView);

        titleComic = itemView.findViewById(R.id.titleComic);

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

        titleComic.setText(cards.getTitle());

    }
}
