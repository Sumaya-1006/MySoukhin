package com.example.mysoukhin.models;

public class CategoryModel {
    private int img_url;
    private String name;

    public CategoryModel() {
    }

    public CategoryModel(int img_url, String name) {
        this.img_url = img_url;
        this.name = name;


    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
