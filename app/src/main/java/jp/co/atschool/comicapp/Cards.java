package jp.co.atschool.comicapp;

import java.util.List;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class Cards {
    private List<Card> cards;
    private String title;

    public List<Card> getCards() {
        return cards;
    }
    public String getTitle() {
        return title;
    }


    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
