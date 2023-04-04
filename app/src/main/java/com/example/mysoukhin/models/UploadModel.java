package com.example.mysoukhin.models;

public class UploadModel {
    String product_name;
    String product_type;
    String img;

    public UploadModel() {
    }

    public UploadModel(String product_name, String product_type, String img) {
        this.product_name = product_name;
        this.product_type = product_type;
        this.img = img;

    }

    public String getProduct_name() {return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
