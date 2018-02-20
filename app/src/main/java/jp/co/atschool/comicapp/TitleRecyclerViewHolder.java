package jp.co.atschool.comicapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by shotakimura on 2018/02/17.
 */

public class TitleRecyclerViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView cardsRecyclerView;
    private CardRecyclerViewAdapter cardRecyclerViewAdapter;

    public TextView titleComic;
    public ImageButton titleDelete;
    public ImageButton titleUpdate;

    Realm mRealm;

    View mView;


    public TitleRecyclerViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        titleComic = itemView.findViewById(R.id.titleComic);
        titleDelete = itemView.findViewById(R.id.titleDelete);
//        titleUpdate = itemView.findViewById(R.id.titleUpdate);

        mRealm = Realm.getDefaultInstance();

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

    public void bindViewHolder(final Cards cards) {
        // データをセット
        cardRecyclerViewAdapter.setList((ArrayList) cards.getCards());
        cardRecyclerViewAdapter.notifyDataSetChanged();

        titleComic.setText(cards.getTitle());
    }

    public ImageButton getTitleDelete() {
        return titleDelete;
    }
}
