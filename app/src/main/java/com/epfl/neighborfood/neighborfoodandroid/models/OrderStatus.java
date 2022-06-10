package com.epfl.neighborfood.neighborfoodandroid.models;

/**
 * OrderStatus enumeration represents the status of an order
 */
public enum OrderStatus {
    unassigned("meal unassigned"),
    assigned("assigned"),
    finished("delivered");

    private final String statusLabel;

    /**
     * Setter for the label of the order's status
     *
     * @param statusLabel label of status
     */
    OrderStatus(String statusLabel) {
        this.statusLabel = statusLabel;
    }

    /**
     * Getter for the label of the order's status
     *
     * @return label of status
     */
    public String getStatusLabel() {
        return statusLabel;
    }
}
