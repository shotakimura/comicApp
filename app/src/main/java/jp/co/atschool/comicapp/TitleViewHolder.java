package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shotakimura on 2018/02/14.
 */

public class TitleViewHolder extends RecyclerView.ViewHolder {
    public TextView titleComic;

    public TitleViewHolder(View itemView) {
        super(itemView);
        titleComic = itemView.findViewById(R.id.titleComic);
    }
}
