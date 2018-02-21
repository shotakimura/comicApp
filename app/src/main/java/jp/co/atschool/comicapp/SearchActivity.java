package jp.co.atschool.comicapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    SearchView mSearchView;
    Integer page = 1;

    List<ListItem> containerList = new ArrayList<>();

    ProgressView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        loading = (ProgressView) findViewById(R.id.loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_info_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMain();
            }
        });

        toolbar.inflateMenu(R.menu.search);

        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.menu_search).getActionView();

        mSearchView.setQueryHint("登録したい漫画を入力してください");



        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                mSearchView.clearFocus();
                loading.setVisibility(View.VISIBLE);

              //  final String encodedStr = getURLEncStr(s);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://app.rakuten.co.jp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API_Interface api = retrofit.create(API_Interface.class);
          //      Call<ListItem> call = api.getItem(encodedStr, page);
                Call<ListItem> call = api.getItem(s, page);

                call.enqueue(new Callback<ListItem>() {
                    @Override
                    public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                        ListItem list = response.body();
                        Timber.d(String.valueOf(response));


                        Integer count = list.getCount();

                            if (count > 0) {
                                containerList.add(list);

                                Integer pageCount = count / 30;

                                if (pageCount == 0) {
                                    makeResultView(s);
                                } else {
                                    getResult(s, page + 1, pageCount - 1);
                                }
                            } else {
                                makeToast(s + "が見つかりません");
                                loading.setVisibility(View.INVISIBLE);

                        }

                    }

                    @Override
                    public void onFailure(Call<ListItem> call, Throwable t) {
                        Timber.d("foooooooooo");
                        makeToast("通信が失敗しました");
                        returnMain();
                    }

                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setIconified(false);
        mSearchView.setFocusable(true);
        mSearchView.requestFocus();


    }

    public void makeToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    //もうちょっときれいにしたい
    public  void getResult(final String s, final Integer page, final Integer pageCount) {
     //   final String encodedStr = getURLEncStr(s);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://app.rakuten.co.jp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API_Interface api = retrofit.create(API_Interface.class);
      //  Call<ListItem> call = api.getItem(encodedStr, page);
        Call<ListItem> call = api.getItem(s, page);


        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                ListItem list = response.body();
                containerList.add(list);
                if(pageCount == 0) {
                    makeResultView(s);
                } else {
                    getResult(s, page+1, pageCount-1);
                }
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                Timber.d("foooooooooo");
                returnMain();
            }
        });

    }

    private String getURLEncStr(String s) {
        if(s == null) return null;
        try {
            return URLEncoder.encode(s , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void makeResultView(String s) {
        SearchFragment fragment = new SearchFragment();

        ContainerList container = new ContainerList();
        container.setContainer(containerList);
        //container.container = containerList;

        Bundle arg = new Bundle();
        //値を渡す
        arg.putString("queryString", s);
        arg.putSerializable("CLASS", container);
        fragment.setArguments(arg);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.frame, fragment);
        // 最後にcommitを使用することで変更を反映します
        transaction.commit();
        loading.setVisibility(View.INVISIBLE);
    }

    private void returnMain() {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
