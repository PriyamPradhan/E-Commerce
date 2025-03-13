package com.priyamdev.ecom.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "status")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter
    @Setter
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    public enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELED
    }
}
