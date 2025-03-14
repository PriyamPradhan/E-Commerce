package com.priyamdev.ecom.entity;

import com.priyamdev.ecom.entity.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Builder
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(precision = 10, scale = 2)
    private Double amount;

    @Column(nullable = false)
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "valid_until")
    private Date validUntil;

    @Column(name = "errorMessage", nullable = true)
    private String errorMessage;

//    public enum PaymentMethod {
//        CREDIT_CARD,
//        DEBIT_CARD,
//        PAYPAL,
//        UPI,
//        CASH_ON_DELIVERY
//    }


    private static final long serialVersionUID = 1L;

}
