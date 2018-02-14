package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shotakimura on 2018/02/14.
 */

public class TitleRecyclerViewAdapter extends RecyclerView.Adapter<TitleViewHolder>{

    private final List<Cards> titles;
    private Cards cards;

    public TitleRecyclerViewAdapter(List<Cards> titles) {
        this.titles = titles;
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent,false);
        TitleViewHolder vh = new TitleViewHolder(inflate);

        return vh;
    }

    @Override
    public void onBindViewHolder(TitleViewHolder holder, int position) {
        holder.titleComic.setText(cards.getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
