package com.example.mysoukhin.models;

public class UploadModel {
    String user_name;
    String product_type;
    String img;

    public UploadModel(){

    }

    public UploadModel(String user_name, String product_type, String img) {
        this.user_name = user_name;
        this.product_type = product_type;
        this.img = img;

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
