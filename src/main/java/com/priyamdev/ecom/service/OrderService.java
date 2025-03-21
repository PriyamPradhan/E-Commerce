package com.priyamdev.ecom.service;

import com.priyamdev.ecom.entity.Order;
import com.priyamdev.ecom.entity.Product;
import com.priyamdev.ecom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.priyamdev.ecom.entity.enums.OrderStatus.PENDING;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    private final ProductService productService;

    @Autowired  // ✅ Autowiring is optional for single-constructor classes
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, ProductService productService) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.productService = productService;
    }



    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
//        Double total = 0.0;
//        for (Product item : order.getItems()) {
//            Product product = productService.getProductById(item.getProductId());
//            total += item.getQuantity() * product.getPrice();
//        }
//        order.setTotal(total);
//
//        if (total <= 0) {
//            throw new IllegalArgumentException("Order total must be greater than zero");
//        }
        return orderRepository.save(order);
    }

    public Order updateOrder(long id, Order order) {
        Order existingOrder = getOrderById(id);
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

    public boolean validateStock(Product product) {
        String productServiceUrl = "http://product-service/products/{id}/stock";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", String.valueOf(product.getProductId()));

        ResponseEntity<Boolean> response = restTemplate.getForEntity(
                productServiceUrl,
                Boolean.class,
                uriVariables
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return false;
    }
}
