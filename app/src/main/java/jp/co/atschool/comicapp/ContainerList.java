package jp.co.atschool.comicapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shotakimura on 2018/01/30.
 */

public class ContainerList implements Serializable {
    private List<ListItem> container;


    public List<ListItem> getContainer() {
        return container;
    }

    public void setContainer(List<ListItem> container) {
        this.container = container;
    }

}
