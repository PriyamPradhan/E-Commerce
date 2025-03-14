package com.priyamdev.ecom.entity;

import com.priyamdev.ecom.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

//    @Column(name = "items")
//    private List<Product> items;

    @ManyToOne
    @JoinColumn(name = "orders", referencedColumnName = "id")
    @Getter
    @Setter
    private User iduser;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

//    public enum OrderStatus {
//        PENDING,
//        COMPLETED,
//        CANCELED
//    }

    private static final long serialVersionUID = 1L;

}
