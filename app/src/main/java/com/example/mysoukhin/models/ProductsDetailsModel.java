package com.example.mysoukhin.models;

public class ProductsDetailsModel {

     String productType;
     String name;
     /*String oldPrice;
     String fabric;*/
     String image;

    public ProductsDetailsModel(String productType, String name,String image) {

        this.productType = productType;
        this.name = name;
        this.image = image;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
