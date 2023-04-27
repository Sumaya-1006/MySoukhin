package com.example.mysoukhin.models;

import java.io.Serializable;

public class CartItemModel implements Serializable {
    private String productImg;
    private String oldPrice;
    private String productPrice;
    private String quantity;
    private String productTitle;
    int totalAmount;
    private boolean CartItemDelete = false;


    public CartItemModel() {
    }

    public CartItemModel(String productImg, String oldPrice, String productPrice, String quantity, String productTitle) {
        this.productImg = productImg;
        this.oldPrice = oldPrice;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.productTitle = productTitle;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public boolean isCartItemDelete() {
        return CartItemDelete;
    }

    public void setCartItemDelete(boolean cartItemDelete) {
        CartItemDelete = cartItemDelete;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}