package jp.co.atschool.comicapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.realm.Realm;

/**
 * Created by shotakimura on 2018/02/14.
 */

public class TitleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final Titles titles;
    private static final int HORIZONTAL_VIEW_TYPE = 0;
    private Realm mRealm;
    private CommicListener listener;

    public interface CommicListener {
        void onCommicRefreshListener();
    }

    public TitleRecyclerViewAdapter(Context context, Titles titles) {
        listener = (CommicListener) context;
        this.titles = titles;

        mRealm = Realm.getDefaultInstance();
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
                final Cards cards = (Cards) titles.getTitles().get(position);
                titleRecyclerViewHolder.bindViewHolder(cards);
                titleRecyclerViewHolder.getTitleDelete().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                ComicTitle comicTitle = realm.where(ComicTitle.class).equalTo("title", cards.getTitle()).findFirst();
                                comicTitle.deleteFromRealm();
                                makeToastHolder(v, cards.getTitle() + "を削除しました");

                                // ここにlistener
                                listener.onCommicRefreshListener();
                            }
                        });
                    }
                });
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

    public void updateCards(Cards cards) {
        
    }

    private void makeToastHolder(View view, String s) {
        Toast.makeText(view.getContext(),s,Toast.LENGTH_LONG).show();
    }

}
