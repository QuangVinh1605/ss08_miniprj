package com.example.productservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(1L, "Laptop Dell XPS", new BigDecimal("1500.00")));
        productRepository.save(new Product(2L, "MacBook Pro M2", new BigDecimal("2000.00")));
    }
}
