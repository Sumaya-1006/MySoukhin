package com.example.mysoukhin.models;

import java.io.Serializable;

public class ProductsModel implements Serializable {
    String productImg;
    String productTitle;
    String productPrice;
    String oldPrice;
    String category;
    boolean IsFavorite;
    int count;

    public ProductsModel(boolean IsFavorite,int count) {
        this.IsFavorite = IsFavorite;
        this.count = count;

    }

    public ProductsModel() {
    }

    public ProductsModel(String productImg, String productTitle, String productPrice, String oldPrice, String category) {
        this.productImg = productImg;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
