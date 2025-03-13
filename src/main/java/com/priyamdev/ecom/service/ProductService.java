package com.priyamdev.ecom.service;

import com.priyamdev.ecom.entity.Product;
import com.priyamdev.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Example of business logic: Update stock
    public void updateStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
        }
    }
}
