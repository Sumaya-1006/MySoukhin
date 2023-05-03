package com.example.mysoukhin.models;

public class ReviewModel {
    String productImg;
    String productTitle;
    String rating;
    String reviews;

    public ReviewModel() {
    }

    public ReviewModel(String productImg, String productTitle, String rating, String reviews) {
        this.productImg = productImg;
        this.productTitle = productTitle;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

}
