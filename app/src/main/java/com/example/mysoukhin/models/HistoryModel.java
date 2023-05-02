package com.example.mysoukhin.models;

public class HistoryModel {
    String productImg;
    String productPrice;
    String productTitle;
    String quantity;

    public HistoryModel() {
    }

    public HistoryModel(String productImg, String productPrice, String productTitle, String quantity) {
        this.productImg = productImg;
        this.productPrice = productPrice;
        this.productTitle = productTitle;
        this.quantity = quantity;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
