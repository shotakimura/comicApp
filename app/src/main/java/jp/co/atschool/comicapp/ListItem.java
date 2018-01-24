package jp.co.atschool.comicapp;

import java.util.List;

/**
 * Created by shotakimura on 2018/01/22.
 */

public class ListItem {
    public List<Item> Items;

    public Integer count;

    public List<Item> getListItems() {
        return Items;
    }

    public void setListItems(List<Item> Items) {
        this.Items = Items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
