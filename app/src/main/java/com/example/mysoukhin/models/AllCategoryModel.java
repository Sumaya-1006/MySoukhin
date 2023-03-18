package com.example.mysoukhin.models;

import java.io.Serializable;

public class AllCategoryModel implements Serializable {
    int productImg;
    String productTitle;
    String productPrice;
    String oldPrice;
    String category;

    public AllCategoryModel(int productImg, String productTitle, String productPrice, String oldPrice, String category) {
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.oldPrice = oldPrice;
        this.category = category;
    }

    public int getProductImg() {
        return productImg;
    }

    public void setProductImg(int productImg) {
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
}
