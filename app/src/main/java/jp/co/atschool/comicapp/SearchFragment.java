package jp.co.atschool.comicapp;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by shotakimura on 2018/01/22.
 */

public class SearchFragment extends Fragment {

    private TextView mTextView;
    //検索したワード
    private String searchWord;
    //検索結果のリスト
    List<Items> Items = new ArrayList<>();
    //チェックボックスの値のリスト
    List<Boolean> checks = new ArrayList<>();

    Realm mRealm;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getDefaultInstance();

        Bundle arg = getArguments();
        if (arg != null) {
            searchWord = arg.getString("queryString");
            ContainerList container = (ContainerList)arg.getSerializable("CLASS");
           // Timber.d("size: " + container.getContainer().size());
           for(int i = 0; i < container.getContainer().size(); i++) {
               Items.addAll(container.getContainer().get(i).getListItems());
           }

        }


    }

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        final Context context = view.getContext();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.checkListRecyclerView);
        ChecklistRecyclerViewAdapter adapter = new ChecklistRecyclerViewAdapter(this.createCheckList(Items));

        LinearLayoutManager llm = new LinearLayoutManager(context);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

        //checkBox のON/OFFを配列に格納
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = view.findViewById(view.getId());
                checks.set(view.getId(), checkBox.isChecked());
            }
        });

        // 先ほどのレイアウトをここでViewとして作成します
        return view;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.searchText);

        mTextView.setText(searchWord);

        //save-buttonを押したら保存
        FloatingActionButton saveFab = view.findViewById(R.id.saveFab);
        saveFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Number max = realm.where(ComicTitle.class).max("id");
                        long newId = 0;
                        if(max != null) {
                            newId = max.longValue() + 1;
                        }
                        ComicTitle comicTitle = realm.createObject(ComicTitle.class, newId);
                        EditText titleText = view.findViewById(R.id.searchText);
                        SpannableStringBuilder saveTitle = (SpannableStringBuilder) titleText.getText();
                        comicTitle.setTitle(saveTitle.toString());
                        List<Comic> comics = makeSaveData();
                        comicTitle.setComics(comics);
                        Toast.makeText(view.getContext(),comicTitle.getTitle() + "を保存しました",Toast.LENGTH_SHORT).show();
                        startMain();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    private List<CheckList> createCheckList(List<Items> items) {

        List<CheckList> dataSet = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            CheckList data = new CheckList();
            data.setTitle(items.get(i).getItem().getTitle());
            dataSet.add(data);

            checks.add(true);
        }
        return dataSet;
    }

    public List<Comic> makeSaveData() {
        List<Comic> comics = new ArrayList<>();

        for (int i = 0; i < Items.size(); i++) {
            if(checks.get(i)) {
                Comic comic = new Comic();
                comic.setId(i);
                comic.setTitle(Items.get(i).getItem().getTitle());
                comic.setTitle(Items.get(i).getItem().getTitleKana());
                comic.setSeriesName(Items.get(i).getItem().getSeriesName());
                comic.setSeriesNameKana(Items.get(i).getItem().getSeriesNameKana());
                comic.setAuthor(Items.get(i).getItem().getAuthor());
                comic.setAuthorKana(Items.get(i).getItem().getAuthorKana());
                comic.setPublisherName(Items.get(i).getItem().getPublisherName());
                comic.setIsbn(Items.get(i).getItem().getIsbn());
                comic.setSalesDate(Items.get(i).getItem().getSalesDate());
                comic.setItemPrice(Items.get(i).getItem().getItemPrice());
                comic.setItemUrl(Items.get(i).getItem().getItemUrl());
                comic.setLargeImageUrl(Items.get(i).getItem().getLargeImageUrl());
                comic.setMediumImageUrl(Items.get(i).getItem().getMediumImageUrl());
                comic.setSmallImageUrl(Items.get(i).getItem().getSmallImageUrl());
                comics.add(comic);
            }
        }
        
        return comics;
    }

    public void startMain(){
       getFragmentManager().beginTransaction().remove(this).commit();
       //fragmentを消してからページ遷移するS
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(new Intent(intent));

    }

}

