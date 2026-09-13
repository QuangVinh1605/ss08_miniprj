package com.example.orderservice.controller;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.dto.ProductDTO;
import com.example.orderservice.dto.UserDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public OrderController(OrderRepository orderRepository, UserClient userClient, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.productClient = productClient;
    }

    @GetMapping("/{id}/details")
    public OrderResponseDTO getOrderDetails(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Gọi đồng bộ qua User Service
        UserDTO user = userClient.getUserById(order.getUserId());
        
        // Gọi đồng bộ qua Product Service
        ProductDTO product = productClient.getProductById(order.getProductId());

        // Tổng hợp dữ liệu
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(order.getId());
        response.setCustomerName(user.getName());
        response.setCustomerAddress(user.getAddress());
        response.setProductName(product.getName());
        response.setQuantity(order.getQuantity());
        
        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(order.getQuantity()));
        response.setTotalPrice(totalPrice);

        return response;
    }
}
