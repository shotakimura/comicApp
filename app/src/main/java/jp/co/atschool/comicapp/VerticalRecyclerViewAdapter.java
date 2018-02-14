package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private ArrayList<Object> mList;
    private static final int HORIZONTAL_VIEW_TYPE = 0;

    public void setList(ArrayList list) {
        mList = list;
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mList.get(position);
        if (item instanceof Cards) {
            return HORIZONTAL_VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HORIZONTAL_VIEW_TYPE:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title, parent, false);
                return new HorizontalRecyclerViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HORIZONTAL_VIEW_TYPE:
                HorizontalRecyclerViewHolder
                        horizontalRecyclerViewHolder = (HorizontalRecyclerViewHolder) holder;
                // 横に並ぶアイテムをセット
                Cards Cards = (Cards) mList.get(position);
                horizontalRecyclerViewHolder.bindViewHolder(Cards);
                break;
        }
    }

}
