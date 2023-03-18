package com.example.mysoukhin.models;

public class ProfileModel {
    private String name;
    private String email;
    private String imgUrl;

    public ProfileModel() {
    }

    public ProfileModel(String name, String email, String imgUrl) {
        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImg(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
