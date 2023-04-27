package com.example.mysoukhin.models;

public class FavouritesClass {
    String productImg;
    String productTitle;
    String productPrice;
    String oldPrice;
    boolean isFavorite;

    public FavouritesClass() {
    }

    public FavouritesClass(String productImg, String productTitle, String productPrice, String oldPrice, boolean isFavorite) {
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.oldPrice = oldPrice;
        this.isFavorite = isFavorite;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
