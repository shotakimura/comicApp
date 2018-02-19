package jp.co.atschool.comicapp;

import io.realm.RealmObject;

/**
 * Created by shotakimura on 2018/01/31.
 */

public class Comic extends RealmObject {
//    @PrimaryKey
//    private long id;
    private String title;
    private String titleKana;
    private String seriesName;
    private String seriesNameKana;
    private String author;
    private String authorKana;
    private String publisherName;
    private String isbn;
    private String salesDate;
    private String itemPrice;
    private String itemUrl;
    private String smallImageUrl;
    private String mediumImageUrl;
    private String largeImageUrl;

//    public long getId() {
//        return id;
//    }
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
    public String getSmallImageUrl() {
        return smallImageUrl;
    }
    public String getMediumImageUrl() {
        return mediumImageUrl;
    }
    public String getLargeImageUrl() {
        return largeImageUrl;
    }

//    public void setId(long id) {
//        this.id = id;
//    }
    public void setTitle(String title) {
        String saveTitle;
        saveTitle = title.replace("（", " ");
        saveTitle = saveTitle.replace("）", "");
        this.title = saveTitle;
    }
    public void setTitleKana(String titleKana) {
        this.titleKana = titleKana;
    }
    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
    public void setSeriesNameKana(String seriesNameKana) {
        this.seriesNameKana = seriesNameKana;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setAuthorKana(String authorKana) {
        this.authorKana = authorKana;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }
    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }
    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }
}
