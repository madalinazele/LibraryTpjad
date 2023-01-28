package com.library.core.service;

import com.library.core.model.Order;

import java.util.Optional;

public interface OrderService {

    Optional<Order> save(Order order);

    Optional<Order> checkout(Order order);
}
