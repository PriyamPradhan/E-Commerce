package com.priyamdev.ecom.service;

import com.priyamdev.ecom.entity.Order;
import com.priyamdev.ecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.priyamdev.ecom.entity.enums.OrderStatus.PENDING;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        Order existingOrder = getOrderById(order.getOrderId());
        if (existingOrder != null) {
            existingOrder.setStatus(order.getStatus());
            return orderRepository.save(order);
        }else {
            return null;
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Example of business logic: Place order
    public Order placeOrder(Order order) {
        order.setStatus(PENDING);
        return orderRepository.save(order);
    }
}
