package com.priyamdev.ecom.entity.enums;

import lombok.Getter;


@Getter
public enum PaymentMethod {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    PAYPAL("PayPal"),
    UPI("UPI"),
    CASH_ON_DELIVERY("COD");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}