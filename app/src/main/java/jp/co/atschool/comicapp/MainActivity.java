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

public class MainActivity extends AppCompatActivity {

    Realm mRealm;

    private RecyclerView mVerticalSampleRecyclerView;
    private VerticalRecyclerViewAdapter mVerticalRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mRealm = Realm.getDefaultInstance();
//
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                RealmResults<ComicTitle> comicTitles = realm.where(ComicTitle.class).findAll();
//                for (ComicTitle comicTitle:comicTitles
//                     ) {
//                    Timber.d("title: " + comicTitle.getTitle());
//                }
//            }
//        });
//
//
//        RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
//        CardRecyclerViewAdapter adapter = new CardRecyclerViewAdapter(this.createCards());
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.HORIZONTAL); // ここで横方向に設定
//
//        rv.setHasFixedSize(true);
//
//        rv.setLayoutManager(llm);
//
//        rv.setAdapter(adapter);

        ArrayList<Cards> cardsSet = new ArrayList<>();
        cardsSet.add(createCards());
        cardsSet.add(createCards());
        cardsSet.add(createCards());

        Titles titles = new Titles();
        titles.setTitles(cardsSet);

        RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
        TitleRecyclerViewAdapter adapter = new TitleRecyclerViewAdapter(titles);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL); // ここで横方向に設定

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

     /*   mVerticalSampleRecyclerView = findViewById(R.id.cardRecyclerView);

        // LayoutManager
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mVerticalSampleRecyclerView.setLayoutManager(linearLayoutManager);

        // Adapter
        mVerticalRecyclerViewAdapter = new VerticalRecyclerViewAdapter();
        mVerticalSampleRecyclerView.setAdapter(mVerticalRecyclerViewAdapter);

        // リストをセット
        ArrayList<Cards> verticalItems = new ArrayList<>();
        verticalItems.add(createCards());
        verticalItems.add(createCards());
        verticalItems.add(createCards());
        verticalItems.add(createCards());
        verticalItems.add(createCards());
        verticalItems.add(createCards());
        verticalItems.add(createCards());

        // リストセット・更新
        mVerticalRecyclerViewAdapter.setList(verticalItems);
        mVerticalRecyclerViewAdapter.notifyDataSetChanged(); */
    }

    private Cards createCards() {

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Card card = new Card();
            card.setTitle("ワンピース (" + i + ")");
            card.setSalesDate("2017/" + i + "/10");

            cards.add(card);
        }

        Cards cardset = new Cards();
        cardset.setCards(cards);
        cardset.setTitle("ワンピース");

        return cardset;
    }


    public void startSearch(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}

