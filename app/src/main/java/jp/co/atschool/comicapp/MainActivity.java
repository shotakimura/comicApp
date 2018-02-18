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
    List<ComicTitle> comicTitles = new ArrayList<>();

    private RecyclerView mVerticalSampleRecyclerView;
    private VerticalRecyclerViewAdapter mVerticalRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getDefaultInstance();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ComicTitle> results = realm.where(ComicTitle.class).findAll();
                for (ComicTitle result:results
                     ) {
                    Timber.d("title: " + result.getTitle());
                    Timber.d("comics: " + result.getComics());
                    comicTitles.add(result);
                }
            }
        });


        RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
        CardRecyclerViewAdapter adapter = new CardRecyclerViewAdapter();
        adapter.setList((ArrayList<Card>) createCards().getCards());

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL); // ここで横方向に設定

        rv.setHasFixedSize(true);

        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

//        ArrayList<Cards> cardsSet = new ArrayList<>();
//        cardsSet.add(createCards());
//        cardsSet.add(createCards());
//        cardsSet.add(createCards());
//
//        Titles titles = new Titles();
//        titles.setTitles(cardsSet);
//
//        Timber.d("aaaaaaaa" + String.valueOf(titles));
//
//        RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
//        TitleRecyclerViewAdapter adapter = new TitleRecyclerViewAdapter(titles);
//
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL); // ここで横方向に設定
//
//        rv.setHasFixedSize(true);
//
//        rv.setLayoutManager(llm);
//
//        rv.setAdapter(adapter);

//       mVerticalSampleRecyclerView = findViewById(R.id.cardRecyclerView);
//
//        // LayoutManager
//        LinearLayoutManager linearLayoutManager =
//                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//        mVerticalSampleRecyclerView.setLayoutManager(linearLayoutManager);
//
//        // Adapter
//        mVerticalRecyclerViewAdapter = new VerticalRecyclerViewAdapter();
//        mVerticalSampleRecyclerView.setAdapter(mVerticalRecyclerViewAdapter);
//
//        // リストをセット
//        ArrayList<VerticalItem> verticalItems = new ArrayList<>();
//        verticalItems.add(getVerticalItem());
//        verticalItems.add(getVerticalItem());
//        verticalItems.add(getVerticalItem());
//        verticalItems.add(getVerticalItem());
//        verticalItems.add(getVerticalItem());
//        verticalItems.add(getVerticalItem());
//        verticalItems.add(getVerticalItem());
//
//        // リストセット・更新
//        mVerticalRecyclerViewAdapter.setList(verticalItems);
//        mVerticalRecyclerViewAdapter.notifyDataSetChanged();
    }

    private VerticalItem getVerticalItem() {
        ArrayList viewItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            viewItems.add(new ViewItem());
        }
        VerticalItem verticalItem = new VerticalItem(viewItems);
        return verticalItem;
    }

    private Cards createCards() {

     //   Timber.d(comicTitle.getComics().get(0).getTitle());

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
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

