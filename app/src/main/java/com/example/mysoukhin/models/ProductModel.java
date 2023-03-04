package com.example.mysoukhin.models;

public class ProductModel {

     String productType;
     String price;
     String oldPrice;
     String fabric;
     String image;

    public ProductModel(String productType, String price, String oldPrice, String fabric, String image) {

        this.productType = productType;
        this.price = price;
        this.oldPrice = oldPrice;
        this.fabric = fabric;
        this.image = image;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getFabrication() {
        return fabric;
    }

    public void setFabrication(String fabrication) {
        this.fabric = fabric;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
