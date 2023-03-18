package com.example.mysoukhin.models;

public class FavouritesClass {
    int productImage;
    String productTitle;
    String productPrice;
    String productOldPrice;
    boolean checked;

    public FavouritesClass(int productImage, String productTitle, String productPrice, String productOldPrice, boolean checked) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productOldPrice = productOldPrice;
        this.checked = checked;
    }

    public FavouritesClass() {

    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
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

    public String getProductOldPrice() {
        return productOldPrice;
    }

    public void setProductOldPrice(String productOldPrice) {
        this.productOldPrice = productOldPrice;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
