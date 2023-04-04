package com.example.mysoukhin.models;

import java.io.Serializable;

public class CartItemModel implements Serializable {


    private String productImage;
    private String price;
    private String quantity;
    private String producttitle;
    private boolean CartItemDelete = false;

    public CartItemModel( String productImage, String producttitle, String price, String quantity ) {

        this.productImage = productImage;
        this.price = price;
        this.quantity = quantity;
        this.producttitle = producttitle;

    }

    public CartItemModel() {

    }

    public boolean isCartItemDelete() {
        return CartItemDelete;
    }

    public void setCartItemDelete(boolean cartItemDelete) {
        CartItemDelete = cartItemDelete;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }


}