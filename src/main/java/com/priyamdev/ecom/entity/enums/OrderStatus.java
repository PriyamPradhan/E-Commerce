package com.priyamdev.ecom.entity.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum OrderStatus {
    PENDING("Order is pending"),
    COMPLETED("Order is completed"),
    CANCELLED("Order is cancelled"),
    SHIPPED("Order is shipped");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name();
    }
}