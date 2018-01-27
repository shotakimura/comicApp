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

/**
 * Created by shotakimura on 2018/01/22.
 */

public class SearchFragment extends Fragment {

    private TextView mTextView;
    private String searchWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            searchWord = args.getString("keyword");
        }

    }

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Context context = view.getContext();

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.checkListRecyclerView);
        ChecklistRecycleViewAdapter adapter = new ChecklistRecycleViewAdapter(this.createCheckList());

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

    private List<CheckList> createCheckList() {

        List<CheckList> dataSet = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            CheckList data = new CheckList();
            data.setTitle(i + "巻");
            dataSet.add(data);
        }
        return dataSet;
    }
    
}

