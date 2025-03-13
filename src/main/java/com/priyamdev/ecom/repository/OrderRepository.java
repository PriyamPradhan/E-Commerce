package com.priyamdev.ecom.repository;

import com.priyamdev.ecom.entity.Order;
import com.priyamdev.ecom.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(OrderStatus status);

    Order findByOrderId(Long orderId);
}