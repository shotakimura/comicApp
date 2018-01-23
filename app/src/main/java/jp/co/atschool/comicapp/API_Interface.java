package jp.co.atschool.comicapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shotakimura on 2018/01/19.
 */

public interface API_Interface {
   // @GET("services/api/BooksBook/Search/20170404?format=json&size=9&booksGenreId=001001&applicationId=1067988941835934400")
    //Call<ListItem> getItem(@Query("title") String title);

    @GET("services/api/BooksBook/Search/20170404?format=json&size=9&booksGenreId=001001&applicationId=1067988941835934400")
    Call<Test> getCount(@Query("title") String title);

}

