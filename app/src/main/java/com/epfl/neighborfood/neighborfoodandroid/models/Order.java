package com.epfl.neighborfood.neighborfoodandroid.models;

import java.util.Date;

public class Order extends Model {

    private static final String ORDER_DELIVERED = "This order was achieved the";
    private static final String ORDER_NOT_YET_DELIVERED = "This order is being processed";
    private Meal meal;
    private Date orderDate;
    private boolean status;
    private String mealVendor;

    public Order(Meal meal, Date orderDate) {
        this.meal = meal;
        this.orderDate = orderDate;
    }

    public Order(Meal meal, Date orderDate, boolean status, String mealVendor) {
        this.meal = meal;
        this.orderDate = orderDate;
        this.status = status;
        this.mealVendor = mealVendor;
    }

    public Meal getMeal() {
        return meal;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMealVendor() {
        return mealVendor;
    }

    public String orderStatusDes() {
        if (status) {
            return ORDER_DELIVERED + orderDate.toString();
        }
        return ORDER_NOT_YET_DELIVERED;
    }

    public Order getOrder() {
        return new Order(meal, orderDate, status, mealVendor);
    }
}