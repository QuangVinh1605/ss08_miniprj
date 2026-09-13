package com.example.orderservice;

import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OrderRepository orderRepository;

    public DataInitializer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // User 1 buys 2 Product 1
        orderRepository.save(new Order(101L, 1L, 1L, 2));
        // User 2 buys 1 Product 2
        orderRepository.save(new Order(102L, 2L, 2L, 1));
    }
}
