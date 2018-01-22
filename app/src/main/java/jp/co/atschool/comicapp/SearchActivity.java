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
            public boolean onQueryTextSubmit(String s) {
                String encodedStr = getURLEncStr(s);

                TextView textView;
                textView = findViewById(R.id.textView);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://app.rakuten.co.jp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API_Interface api = retrofit.create(API_Interface.class);
              //  Call<ListItem> call = api.getItem(encodedStr);
                Call<ListItem> call = api.getItem(encodedStr);

                call.enqueue(new Callback<ListItem>() {
                    @Override
                    public void onResponse(Call<ListItem> call, Response<ListItem> response) {

                    }

                    @Override
                    public void onFailure(Call<ListItem> call, Throwable t) {
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
