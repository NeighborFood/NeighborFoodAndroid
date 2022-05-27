package com.epfl.neighborfood.neighborfoodandroid.models;



import com.epfl.neighborfood.neighborfoodandroid.R;

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
