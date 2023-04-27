package com.example.mysoukhin.models;

import java.io.Serializable;

public class LatestModel implements Serializable {
    String id;
    String productImg;
    String rating;
    String productTitle;
    String productPrice;
    String oldPrice;
    String category;
    boolean IsFavorite;

    public LatestModel(boolean isFavorite) {
        IsFavorite = isFavorite;
    }

    public LatestModel() {

    }

    public LatestModel(String productImg, String rating, String productTitle, String productPrice, String oldPrice, String category) {
        this.productImg = productImg;
        this.rating = rating;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.oldPrice = oldPrice;
        this.category = category;

    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getIsFavorite() {
        return IsFavorite;
    }

    public void setFavorite(boolean favorite) {
        IsFavorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
