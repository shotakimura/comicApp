package jp.co.atschool.comicapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shotakimura on 2018/01/22.
 */

public class ListItem implements Serializable {
    public List<Items> Items;

    public Integer count;

    public List<Items> getListItems() {
        return Items;
    }

    public void setListItems(List<Items> Items) {
        this.Items = Items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
