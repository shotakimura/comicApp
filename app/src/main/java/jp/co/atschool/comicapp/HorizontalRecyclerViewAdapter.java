package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mList;
    private static final int VIEW_ITEM_TYPE = 0;

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
        if (item instanceof VerticalItem) {
            return VIEW_ITEM_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_ITEM_TYPE:
                View itemView =
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
                return new ViewItemHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_ITEM_TYPE:
                ViewItemHolder viewItemHolder = (ViewItemHolder) holder;
                ViewItem viewItem = (ViewItem) mList.get(position);
                viewItemHolder.bindViewHolder(viewItem);
                break;
        }
    }
}
