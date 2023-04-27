package com.example.mysoukhin.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int img_url;
    private String name;
    private String category;

    public CategoryModel() {
    }

    public CategoryModel(int img_url, String name,String category) {
        this.img_url = img_url;
        this.name = name;
        this.category = category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
