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

public class MainActivity extends AppCompatActivity implements TitleRecyclerViewAdapter.CommicRefreshListener, CardRecyclerViewAdapter.CommicListener {

    Realm mRealm;
    List<ComicTitle> comicTitles = new ArrayList<>();
    TitleRecyclerViewAdapter mAdapter = null;

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
                //    Timber.d("title: " + result.getTitle());
                //    Timber.d("comics: " + result.getComics());
                    comicTitles.add(result);
                }
            }
        });

        if(comicTitles.size() > 0) {
//            RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
//            CardRecyclerViewAdapter adapter = new CardRecyclerViewAdapter();
//            adapter.setList((ArrayList<Card>) createCards(comicTitles.get(0)).getCards());
//
//            LinearLayoutManager llm = new LinearLayoutManager(this);
//            llm.setOrientation(LinearLayoutManager.HORIZONTAL); // ここで横方向に設定
//
//            rv.setHasFixedSize(true);
//
//            rv.setLayoutManager(llm);
//
//            rv.setAdapter(adapter);


            ArrayList<Cards> cardsSet = new ArrayList<>();
            for (int i = 0; i < comicTitles.size(); i++) {
                cardsSet.add(createCards(comicTitles.get(i)));
            }

            Titles titles = new Titles();
            titles.setTitles(cardsSet);

     //       Timber.d("aaaaaaaa" + String.valueOf(titles));

            RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
            mAdapter = new TitleRecyclerViewAdapter(this, titles);

            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

            rv.setHasFixedSize(true);

            rv.setLayoutManager(llm);

            rv.setAdapter(mAdapter);

//       mVerticalSampleRecyclerView = findViewById(R.id.cardRecyclerView);
//
//        // LayoutManager
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
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
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        comicRefresh();
    }

    private void comicRefresh() {
        final List<ComicTitle> comicTitlesRefresh = new ArrayList<>();

        RealmResults<ComicTitle> results = mRealm.where(ComicTitle.class).findAll();
        for (ComicTitle result:results) {
            comicTitlesRefresh.add(result);
        }
      //  if(comicTitlesRefresh.size() > 0) {

            ArrayList<Cards> cardsSet = new ArrayList<>();
            for (int i = 0; i < comicTitlesRefresh.size(); i++) {
                cardsSet.add(createCards(comicTitlesRefresh.get(i)));
            }

            Titles titles = new Titles();
            titles.setTitles(cardsSet);

            if(comicTitles.size() == 0 && comicTitlesRefresh.size() > 0) {
                RecyclerView rv = (RecyclerView) findViewById(R.id.cardRecyclerView);
                mAdapter = new TitleRecyclerViewAdapter(this, titles);

                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                rv.setHasFixedSize(true);

                rv.setLayoutManager(llm);

                rv.setAdapter(mAdapter);
            } else {
                mAdapter.updateCards(titles);
            }


      //  }
    }

    @Override
    public void onCommicRefreshListener() {
        comicRefresh();

    }

    @Override
    public void onCommicListener(Card card) {
       // Toast.makeText(this,card.getTitle(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ComicActivity.class);
        intent.putExtra("DATA", card);
        startActivity(intent);
    }

//    private VerticalItem getVerticalItem() {
//        ArrayList viewItems = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            viewItems.add(new ViewItem());
//        }
//        VerticalItem verticalItem = new VerticalItem(viewItems);
//        return verticalItem;
//    }

    private Cards createCards(ComicTitle comicTitle) {

     //   Timber.d(comicTitle.getComics().get(0).getTitle());

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < comicTitle.getComics().size(); i++) {
            Card card = new Card();
            card.setTitle(comicTitle.getComics().get(i).getTitle());
            card.setSalesDate(comicTitle.getComics().get(i).getSalesDate());
            card.setLargeImageUrl(comicTitle.getComics().get(i).getLargeImageUrl());

            card.setAuthor(comicTitle.getComics().get(i).getAuthor());
            card.setPublisherName(comicTitle.getComics().get(i).getPublisherName());
            card.setIsbn(comicTitle.getComics().get(i).getIsbn());
            card.setItemPrice(comicTitle.getComics().get(i).getItemPrice());
            card.setItemUrl(comicTitle.getComics().get(i).getItemUrl());

            cards.add(card);
        }

        Cards cardset = new Cards();
        cardset.setCards(cards);
        cardset.setTitle(comicTitle.getTitle());

        return cardset;
    }


    public void startSearch(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}

