package com.priyamdev.ecom.service;

import com.priyamdev.ecom.entity.Payment;
import com.priyamdev.ecom.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Autowired
    private RestTemplate restTemplate;



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
        try {
            // Call external payment gateway (e.g., PayPal)
            String paymentUrl = "https://api.paypal.com/v1/payments";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(payment.toString(), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(paymentUrl, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                payment.setPaymentStatus("SUCCESS");
            } else {
                payment.setPaymentStatus("FAILED");
            }

            return paymentRepository.save(payment);

        } catch (Exception e) {
            payment.setPaymentStatus("FAILED");
            payment.setErrorMessage(e.getMessage());
            e.printStackTrace();        // will log in future
            return paymentRepository.save(payment);
        }
    }
}