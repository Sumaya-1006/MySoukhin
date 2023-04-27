package com.example.mysoukhin.models;

public class OrderModel {
    String productImg;
    String productPrice;
    String productTitle;

    public OrderModel() {
    }

    public OrderModel(String productImg, String productPrice, String productTitle) {
        this.productImg = productImg;
        this.productPrice = productPrice;
        this.productTitle = productTitle;
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
}
