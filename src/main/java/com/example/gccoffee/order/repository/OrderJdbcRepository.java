package com.example.gccoffee.order.repository;

import com.example.gccoffee.order.domain.Order;
import com.example.gccoffee.order.domain.OrderItem;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderJdbcRepository implements  OrderRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Order insert(Order order) {
        String sql = "INSERT INTO orders(order_id, email, address, postcode, order_status, create_at, update_at)" +
                " values(UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createAt, :updateAt)";

        jdbcTemplate.update(sql, toOrderParamMap(order));

        String itemSql = "INSERT INTO order_items(order_id, product_id, category, price, quantity, create_at, update_at)" +
                " values (UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :category, :price, :quantity, :createAt, :updateAt)";
        order.getOrderItems().forEach(orderItem -> jdbcTemplate.update(itemSql, toOrderItemParamMap(order.getOrderId(), order.getCreateAt(), order.getUpdateAt(), orderItem)));



        return order;
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        var parMap = new HashMap<String, Object>();
        parMap.put("orderId", order.getOrderId().toString().getBytes());
        parMap.put("email", order.getEmail().getEmail());
        parMap.put("address", order.getAddress());
        parMap.put("postcode",  order.getPostcode());
        parMap.put("orderStatus", order.getOrderStatus().toString());
        parMap.put("createAt", order.getCreateAt());
        parMap.put("updateAt", order.getUpdateAt());
        return parMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updateAt, OrderItem item) {
        var parMap = new HashMap<String, Object>();
        parMap.put("orderId", orderId.toString().getBytes());
        parMap.put("productId", item.productId().toString().getBytes());
        parMap.put("category", item.category().toString());
        parMap.put("price", item.price());
        parMap.put("quantity", item.quantity());
        parMap.put("createAt", createdAt);
        parMap.put("updateAt", updateAt);
        return parMap;
    }
}
