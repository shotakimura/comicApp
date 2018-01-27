package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by shotakimura on 2018/01/27.
 */

public class ChecklistViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkTitle;
    public ChecklistViewHolder(View itemView) {
        super(itemView);
        checkTitle = (CheckBox) itemView.findViewById(R.id.checkTitle);
    }
}