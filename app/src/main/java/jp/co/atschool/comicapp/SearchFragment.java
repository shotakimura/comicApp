package jp.co.atschool.comicapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by shotakimura on 2018/01/22.
 */

public class SearchFragment extends Fragment {

    private TextView mTextView;
    private String searchWord;
    List<Items> Items = new ArrayList<>();
    Realm mRealm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getDefaultInstance();

        Bundle arg = getArguments();
        if (arg != null) {
            searchWord = arg.getString("queryString");
        //    listItem = (ListItem)arg.getSerializable("CLASS");
            ContainerList container = (ContainerList)arg.getSerializable("CLASS");
            Timber.d("size: " + container.getContainer().size());
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

        Context context = view.getContext();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.checkListRecyclerView);
        ChecklistRecycleViewAdapter adapter = new ChecklistRecycleViewAdapter(this.createCheckList(Items));

        LinearLayoutManager llm = new LinearLayoutManager(context);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);

        rv.setAdapter(adapter);

        // 先ほどのレイアウトをここでViewとして作成します
        return view;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.searchText);

        mTextView.setText(searchWord);

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
        }
        return dataSet;
    }
    
}

