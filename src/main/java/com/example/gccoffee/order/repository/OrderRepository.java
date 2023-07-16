package com.example.gccoffee.order.repository;

import com.example.gccoffee.order.domain.Order;

public interface OrderRepository {
    Order insert(Order order);
}
