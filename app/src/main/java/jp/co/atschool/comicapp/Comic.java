package jp.co.atschool.comicapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shotakimura on 2018/01/31.
 */

public class Comic extends RealmObject {
    @PrimaryKey
    public long id;
    public String title;
    public String titleKana;
    public String seriesName;
    public String seriesNameKana;
    public String author;
    public String authorKana;
    public String publisherName;
    public String isbn;
    public String salesDate;
    public String itemPrice;
    public String itemUrl;
    public String smallImageUrl;
    public String mediumImageUrl;
    public String largeImageUrl;
}
