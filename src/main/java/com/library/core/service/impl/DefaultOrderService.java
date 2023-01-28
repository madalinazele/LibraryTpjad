package com.library.core.service.impl;

import com.library.core.model.Order;
import com.library.core.model.OrderEntry;
import com.library.core.model.Product;
import com.library.core.repository.OrderRepository;
import com.library.core.service.OrderService;
import com.library.core.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultOrderService implements OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;

    @Override
    public Optional<Order> save(Order order) {
        return orderRepository.save(order) ? Optional.of(order) : Optional.empty();
    }

    @Override
    public Optional<Order> checkout(Order order) {
        Optional<Order> savedOrder = save(order);

        removeProducts(order.getOrderEntries());
        return savedOrder;
    }

    public void removeProducts(List<OrderEntry> orderEntryList) {
        for (OrderEntry orderEntry : orderEntryList) {
            final Product product = orderEntry.getProduct();
            product.setStock(product.getStock() - orderEntry.getQuantity());
            productService.update(product);
        }
    }
}
