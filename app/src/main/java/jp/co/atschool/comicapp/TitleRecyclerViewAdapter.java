package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shotakimura on 2018/02/14.
 */

public class TitleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Titles titles;
    private static final int HORIZONTAL_VIEW_TYPE = 0;

    public TitleRecyclerViewAdapter(Titles titles) {
        this.titles = titles;
    }

    public int getItemViewType(int position) {
        Object item = titles.getTitles().get(position);
        if (item instanceof Cards) {
            return HORIZONTAL_VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HORIZONTAL_VIEW_TYPE:
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent, false);
                return new TitleRecyclerViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HORIZONTAL_VIEW_TYPE:
                TitleRecyclerViewHolder
                        titleRecyclerViewHolder = (TitleRecyclerViewHolder) holder;
                // 横に並ぶアイテムをセット
                Cards Cards = (Cards) titles.getTitles().get(position);
                titleRecyclerViewHolder.bindViewHolder(Cards);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (titles != null) {
            return titles.getTitles().size();
        }
        return 0;
    }
}
