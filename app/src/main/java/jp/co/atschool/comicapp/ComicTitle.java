
package jp.co.atschool.comicapp;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shotakimura on 2018/02/07.
 */


public class ComicTitle extends RealmObject {
    @PrimaryKey
    private long id;

    private String title;

    @Ignore
    private List<Comic> comics;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }


}
