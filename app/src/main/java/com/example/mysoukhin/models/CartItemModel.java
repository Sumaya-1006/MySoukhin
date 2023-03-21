package com.example.mysoukhin.models;

public class CartItemModel {

    public static final int cart_item = 0  ;
    private  int type;

    private int productImage;
    private String price;
    private int quantity;
    private String producttitle;
    private boolean CartItemDelete = false;
    public CartItemModel( int productImage, String producttitle, String price, int quantity ) {
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }


}