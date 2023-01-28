package com.library.core.service;

import com.library.core.model.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> save(Cart cart);

    boolean update(Cart cart);

    boolean delete(Integer id);

    Optional<Cart> getById(Integer id);

    boolean addToCart(Cart cart, Integer productId, Integer quantity);

    boolean deleteFromCart(Integer cartEntryId);

    boolean updateCartEntry(Cart cart, Integer cartEntryId, Integer quantity);
}
