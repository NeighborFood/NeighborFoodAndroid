package com.epfl.neighborfood.neighborfoodandroid.models;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Order extends Model {

    private static final String ORDER_DELIVERED = "This order was achieved the";
    private static final String ORDER_NOT_YET_DELIVERED = "This order is being processed";
    private String orderId;
    private String mealId;
    private String orderDate;
    private boolean status;
    private String vendorId;
    private String buyerId;


    public Order(String s, Date time, boolean b, String s1, String s2){
    }
    public Order(String orderId, String mealId, Date orderDate, boolean status, String VendorId, String buyerId) {
        this.orderId = orderId;
        this.mealId = mealId;
        this.orderDate = orderDate.toString();
        this.status = status;
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
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getStatus() {
        return status;
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

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /*
    returns description about the order status
     */
    public String orderStatusDes() {
        if (status) {
            return ORDER_DELIVERED + orderDate.toString();
        }
        return ORDER_NOT_YET_DELIVERED;
    }
}