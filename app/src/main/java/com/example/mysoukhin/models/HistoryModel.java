package com.example.mysoukhin.models;

public class HistoryModel {
    private String OrderID, Date, orderNums, orderProducts, OrderCheck;

    public HistoryModel(String OrderId, String date, String orderNums, String orderProducts, String OrderCheck) {
        this.OrderID = OrderId;
        Date = date;
        this.orderNums = orderNums;
        this.orderProducts = orderProducts;
        this.OrderCheck = OrderCheck;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(String orderNums) {
        this.orderNums = orderNums;
    }

    public String getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(String orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getOrderCheck() {
        return OrderCheck;
    }

    public void setOrderCheck(String orderCheck) {
        OrderCheck = orderCheck;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }
}


