package com.priyamdev.ecom.repository;

import com.priyamdev.ecom.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

//    Payment findByOrderId(Long orderId);
//
//    List<Payment> findByStatus(String status);
//
//    List<Payment> findByPaymentMethod(String paymentMethod);
}
