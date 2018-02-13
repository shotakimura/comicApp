package jp.co.atschool.comicapp;

/**
 * Created by shotakimura on 2018/02/13.
 */

public class Card {
    private String title;
    private String salesDate;
    private String largeImageUrl;

    public String getTitle() {
        return title;
    }
    public String getSalesDate() {
        return salesDate;
    }
    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }
    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

}
