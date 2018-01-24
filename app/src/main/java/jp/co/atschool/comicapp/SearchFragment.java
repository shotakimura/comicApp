package jp.co.atschool.comicapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        // 先ほどのレイアウトをここでViewとして作成します
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.searchText);

        mTextView.setText(searchWord);

    }
}

