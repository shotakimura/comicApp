package jp.co.atschool.comicapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.search);

        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.menu_search).getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                String encodedStr = getURLEncStr(s);

                final TextView textView;
                textView = findViewById(R.id.textView);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://app.rakuten.co.jp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API_Interface api = retrofit.create(API_Interface.class);
            /*    Call<ListItem> call = api.getItem(encodedStr);

                call.enqueue(new Callback<ListItem>() {
                    @Override
                    public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                        ListItem list = response.body();
                        List<Item> items = list.getListItems();
                        Item item = items.get(0);
                        Timber.d(call.toString());
                        Timber.d(response.toString());
                        Timber.d(list.toString());
                        Timber.d(items.toString());
                        for (Item item1 : items) {
                            Timber.d("hogehoge: " + item1.title);
                        }
                        Timber.d(item.toString());
                        Timber.d("faaaaaaaaa");
                        textView.setText(item.getTitle());
                    }

                    @Override
                    public void onFailure(Call<ListItem> call, Throwable t) {
                        Timber.d("foooooooooo");
                    }

                }); */

                Call<Test> call = api.getCount(encodedStr);

                call.enqueue(new Callback<Test>() {
                    @Override
                    public void onResponse(Call<Test> call, Response<Test> response) {

                        Integer count = response.body().getCount();
                        Integer count2 = response.body().pageCount;

                        Timber.d("hoge: " + count);
                        Timber.d("hogehoge: " + count2);

                      //  textView.setText(title);
                    }

                    @Override
                    public void onFailure(Call<Test> call, Throwable t) {
                        Timber.d("foooooooooo");
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

    private String getURLEncStr(String s) {
        if(s == null) return null;
        try {
            return URLEncoder.encode(s , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
