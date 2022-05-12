package com.epfl.neighborfood.neighborfoodandroid.models;

import java.util.Date;

public class Order extends Model {

    private static final String ORDER_DELIVERED = "This order was achieved the";
    private static final String ORDER_NOT_YET_DELIVERED = "This order is being processed";
    private String orderId;
    private String mealId;
    private Date orderDate;
    private boolean status;
    private String vendorId;
    private String buyerId;


    public Order(String orderId, String mealId, Date orderDate, boolean status, String VendorId, String buyerId) {
        this.orderId = orderId;
        this.mealId = mealId;
        this.orderDate = orderDate;
        this.status = status;
        this.vendorId = VendorId;
        this.buyerId = buyerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getMealId() {
        return mealId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String getVendorId() {
        return vendorId;
    }

    public String getBuyerIdId() {
        return vendorId;
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
    public Order copyWithId(String orderId){
        return new Order(orderId, mealId, orderDate, status, vendorId, buyerId);
    }
    public Order getOrder() {
        return new Order(orderId, mealId, orderDate, status, vendorId, buyerId);
    }
}