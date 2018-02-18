package jp.co.atschool.comicapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by shotakimura on 2018/02/17.
 */

public class ViewItemHolder extends RecyclerView.ViewHolder {

    public TextView title;

    public ViewItemHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }

    public void bindViewHolder(ViewItem viewItem) {
        // imageCard.setImageResource(card.getLargeImageUrl());
        title.setText(viewItem.title);
    }
}
