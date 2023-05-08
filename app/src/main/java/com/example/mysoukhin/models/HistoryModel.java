package com.example.mysoukhin.models;

public class HistoryModel {
    String OrderId,  Date, orderNums, orderPrice, orderProducts, OrderCheck;

    public HistoryModel() {
    }

    public HistoryModel(String OrderId, String date, String orderNums, String OrderCheck,String orderProducts) {
        Date = date;
        this.orderNums = orderNums;
        this.OrderCheck = OrderCheck;
        this.OrderId = OrderId;
        this.orderProducts = orderProducts;

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

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
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

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }


}


