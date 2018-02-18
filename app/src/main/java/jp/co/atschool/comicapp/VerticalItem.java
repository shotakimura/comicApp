package jp.co.atschool.comicapp;

import java.util.List;

/**
 * Created by shotakimura on 2018/02/17.
 */

public class VerticalItem {
    List<ViewItem> viewItems;

    public VerticalItem(List<ViewItem> viewItems) {
        this.viewItems = viewItems;
    }

    public List<ViewItem> getList() {
        return viewItems;
    }


}
