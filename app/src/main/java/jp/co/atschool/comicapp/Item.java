package jp.co.atschool.comicapp;

/**
 * Created by shotakimura on 2018/01/22.
 */

public class Item {
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

    public String getTitle() {
        return title;
    }
    public String getTitleKana() {
        return titleKana;
    }
    public String getSeriesName() {
        return seriesName;
    }
    public String getSeriesNameKana() {
        return seriesNameKana;
    }
    public String getAuthor() {
        return author;
    }
    public String getAuthorKana() {
        return authorKana;
    }
    public String getPublisherName() {
        return publisherName;
    }
    public String getIsbn() { return isbn; }
    public String getSalesDate() {
        return salesDate;
    }
    public String getItemPrice() {
        return itemPrice;
    }
    public String getItemUrl() {
        return itemUrl;
    }
    public String getSmallImageUrl() {
        return smallImageUrl;
    }
    public String getMediumImageUrl() {
        return mediumImageUrl;
    }
    public String getLargeImageUrl() {
        return largeImageUrl;
    }



    public void setTitle(String title) {
        this.title = title;
    }
}
