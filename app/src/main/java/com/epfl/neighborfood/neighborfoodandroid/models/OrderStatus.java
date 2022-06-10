package com.epfl.neighborfood.neighborfoodandroid.models;


public enum OrderStatus {
    unassigned("meal unassigned"),
    assigned("assigned"),
    finished("delivered");

    private final String statusLabel;

    OrderStatus(String statusLabel){
        this.statusLabel = statusLabel;
    }

    public String getStatusLabel() {
        return statusLabel;
    }
}
