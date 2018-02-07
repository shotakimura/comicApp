package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shotakimura on 2018/01/27.
 */

public class ChecklistRecycleViewAdapter extends RecyclerView.Adapter<ChecklistViewHolder>{
    private List<CheckList> list;

    private View.OnClickListener listener;

    public ChecklistRecycleViewAdapter(List<CheckList> list) {
        this.list = list;
    }

    @Override
    public ChecklistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.checklist, parent,false);
        ChecklistViewHolder vh = new ChecklistViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChecklistViewHolder holder, int position) {
        holder.checkTitle.setText(list.get(position).getTitle());
        holder.checkTitle.setId(holder.getAdapterPosition());
        holder.checkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
