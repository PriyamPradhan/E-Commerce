package com.priyamdev.ecom.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "status")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELED
    }
}
