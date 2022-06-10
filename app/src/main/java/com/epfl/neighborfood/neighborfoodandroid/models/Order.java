package com.epfl.neighborfood.neighborfoodandroid.models;

import java.util.Date;

/**
 * Order class contains all the information about the transaction of a certain meal
 */
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
    private PickupLocation location;

    /**
     * Constructor of the order
     *
     * @param orderId     id of order
     * @param mealId      id of meal corresponding to order
     * @param orderDate   date of order's change of status
     * @param orderStatus status of the order
     * @param VendorId    id of the vendor
     * @param buyerId     id of the buyer
     * @param location    pickup location
     * @param price       price of the meal
     */
    public Order(String orderId, String mealId, Date orderDate, OrderStatus orderStatus, String VendorId, String buyerId, PickupLocation location, double price) {
        this.orderId = orderId;
        this.mealId = mealId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.vendorId = VendorId;
        this.buyerId = buyerId;
        this.location = location;
        this.price = price;
    }

    public Order() {
    }

    /**
     * Getter for order ID
     *
     * @return order ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Getter for meal ID corresponding to the order
     *
     * @return meal ID
     */
    public String getMealId() {
        return mealId;
    }

    /**
     * Getter for the order last update date
     *
     * @return date of the last update of order's status
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Getter for the order's status
     *
     * @return status of the order
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Getter for the vendor Id
     *
     * @return vendor Id
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * Getter for the buyer id
     *
     * @return buyer Id
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * Getter for the pickup location
     *
     * @return pickup location of the order
     */
    public PickupLocation getLocation() {
        return location;
    }

    /**
     * Setter for the pickup location
     *
     * @param location pickup location of the order
     */
    public void setLocation(PickupLocation location) {
        this.location = location;
    }

    /**
     * Setter for the order ID
     *
     * @param orderId ID of the order
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Setter for the meal Id
     *
     * @param mealId id of the meal corresponding to the order
     */
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    /**
     * Setter for the date of the order's last update of status
     *
     * @param orderDate date of last update to the order's status
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Setter for the order's status
     *
     * @param orderStatus status of the order
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Setter for the vendor's ID
     *
     * @param vendorId order's vendor ID
     */
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * Setter for the buyer's ID
     *
     * @param buyerId order's buyer ID
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Setter for the order's price
     *
     * @param price price of the meal
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for the order's price
     *
     * @return the price of the order
     */
    public double getPrice() {
        return price;
    }

    /**
     * transform the status into a short text description of it
     *
     * @return the short description corresponding to the order's status
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

    /**
     * transforms the status into a detailed description
     *
     * @return the order status detailed description
     */
    public String orderStatusDes() {
        switch (orderStatus) {
            case finished:
                return ORDER_ACHIEVED + orderDate.toString();
            case assigned:
                return ORDER_NOT_YET_DELIVERED;
            default:
                return ORDER_NOT_YET_ASSIGNED;
        }
    }
}
