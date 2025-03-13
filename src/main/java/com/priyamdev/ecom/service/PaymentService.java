package com.priyamdev.ecom.service;

import com.priyamdev.ecom.entity.Payment;
import com.priyamdev.ecom.entity.Product;
import com.priyamdev.ecom.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Example of business logic: Process payment
    public Payment processPayment(Payment payment) {
        payment.setPaymentStatus("COMPLETED");
        return paymentRepository.save(payment);
    }
}