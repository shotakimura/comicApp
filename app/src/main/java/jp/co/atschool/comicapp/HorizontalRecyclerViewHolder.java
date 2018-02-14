package jp.co.atschool.comicapp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class HorizontalRecyclerViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView mHorizontalRecyclerView;
    private HorizontalRecyclerViewAdapter mHorizontalRecyclerViewAdapter;

    public HorizontalRecyclerViewHolder(View itemView) {
        super(itemView);

        // RecyclerView
        mHorizontalRecyclerView = itemView.findViewById(R.id.recyclerComics);

        // LayoutManager(Horizontal)
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mHorizontalRecyclerView.setLayoutManager(linearLayoutManager);

        // Adapter
        mHorizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter();
        mHorizontalRecyclerView.setAdapter(mHorizontalRecyclerViewAdapter);
    }

    public void bindViewHolder(Cards cards) {
        // データをセット
        mHorizontalRecyclerViewAdapter.setList((ArrayList) cards.getCards());
        mHorizontalRecyclerViewAdapter.notifyDataSetChanged();
    }

}
