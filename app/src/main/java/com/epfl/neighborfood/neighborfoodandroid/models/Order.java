package com.epfl.neighborfood.neighborfoodandroid.models;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Order extends Model {

    private static final String ORDER_ACHIEVED = "This order was achieved the ";
    private static final String ORDER_DELIVERED = "This order was delivered";
    private static final String ORDER_NOT_YET_DELIVERED = "This order is waiting for pickup ";
    private static final String ORDER_NOT_YET_ASSIGNED = "This order is not assigned yet";
    private String orderId;
    private String mealId;
    private Date orderDate;
    private OrderStatus orderStatus;
    private String vendorId;
    private String buyerId;
    private double price;


    public Order(String s, Date time, OrderStatus b, String s1, String s2){
    }
    public Order(String orderId, String mealId, Date orderDate, OrderStatus orderStatus, String VendorId, String buyerId) {
        this.orderId = orderId;
        this.mealId = mealId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.vendorId = VendorId;
        this.buyerId = buyerId;
    }
    public Order(){}

    public String getOrderId() {
        return orderId;
    }

    public String getMealId() {
        return mealId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    /*
        returns short description about the order status
         */
    public String orderShortStatusDes() {
        switch (orderStatus) {
            case finished:
                return ORDER_DELIVERED;
            case assigned:
                return ORDER_NOT_YET_DELIVERED;
            default:
                return ORDER_NOT_YET_ASSIGNED;
        }
    }
        /*
    returns description about the order status
     */
    public String orderStatusDes() {
        switch (orderStatus){
            case finished:
                return ORDER_ACHIEVED + orderDate.toString();
            case assigned:
                return ORDER_NOT_YET_DELIVERED;
            default:
                return ORDER_NOT_YET_ASSIGNED;
        }
    }
}
