package jp.co.atschool.comicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ComicTitle> comicTitles = realm.where(ComicTitle.class).findAll();
                for (ComicTitle comicTitle:comicTitles
                     ) {
                    Timber.d("title: " + comicTitle.getTitle());
                }
            }
        });

        RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
        CardRecycleViewAdapter adapter = new CardRecycleViewAdapter(this.createCards());

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL); // ここで横方向に設定

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);
    }

    private List<Card> createCards() {

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Card card = new Card();
            card.setTitle("ワンピース (" + i + ")");
            card.setSalesDate("2017/" + i + "/10");

            cards.add(card);
        }
        return cards;
    }

    public void startSearch(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}

