package com.example.gccoffee.order.service;

import com.example.gccoffee.order.domain.Email;
import com.example.gccoffee.order.domain.Order;
import com.example.gccoffee.order.domain.OrderItem;
import com.example.gccoffee.order.domain.OrderStatus;
import com.example.gccoffee.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems) {
        Order order = new Order(
                UUID.randomUUID(),
                email,
                address,
                postcode,
                orderItems,
                OrderStatus.ACCEPT,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        orderRepository.insert(order);

        return order;
    }
}
