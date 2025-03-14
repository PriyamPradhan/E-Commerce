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
        if (product.getStock() < 0){
            throw new IllegalArgumentException("Stock cannot be less than zero");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = getProductById(product.getProductId());
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Example of business logic: Update stock
    public Product updateStock(Long productId, Integer quantity) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.setStock(product.getStock() + quantity);
        if (product.getStock() < 0){
            throw new IllegalArgumentException("Stock cannot be less than zero");
        }
        return productRepository.save(product);
    }
}
