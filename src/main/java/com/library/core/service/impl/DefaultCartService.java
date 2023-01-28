package com.library.core.service.impl;

import com.library.core.model.Cart;
import com.library.core.model.CartEntry;
import com.library.core.model.Product;
import com.library.core.repository.CartEntryRepository;
import com.library.core.repository.CartRepository;
import com.library.core.service.CartService;
import com.library.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultCartService implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartEntryRepository cartEntryRepository;

    @Override
    public Optional<Cart> save(Cart cart) {
        if (cartRepository.save(cart)) {
            return Optional.of(cart);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Cart cart) {
        return cartRepository.update(cart);
    }

    @Override
    public boolean delete(Integer id) {
        return cartRepository.deleteById(id);
    }

    @Override
    public Optional<Cart> getById(Integer id) {
        return cartRepository.findById(id);
    }

    @Override
    public boolean addToCart(Cart cart, Integer productId, Integer quantity) {
        Optional<Product> product = productService.getById(productId);
        if (product.isEmpty()) {
            return false;
        }
        Optional<CartEntry> cartEntryOp = cart.getCartEntries().stream()
                .filter(cartEntry -> cartEntry.getProduct().getId().equals(productId))
                .findFirst();
        if (cartEntryOp.isPresent()) {
            cartEntryOp.get().setQuantity(cartEntryOp.get().getQuantity() + quantity);
        } else {
            CartEntry newCartEntry = new CartEntry(null, quantity, product.get(), cart);
            cart.getCartEntries().add(newCartEntry);
        }
        cartRepository.save(cart);
        return true;
    }

    @Override
    public boolean deleteFromCart(Integer cartEntryId) {
        return cartEntryRepository.deleteById(cartEntryId);
    }

    @Override
    public boolean updateCartEntry(Cart cart, Integer cartEntryId, Integer quantity) {
        for (CartEntry cartEntry : cart.getCartEntries()) {
            if (cartEntry.getId().equals(cartEntryId)) {
                cartEntry.setQuantity(quantity);
                return cartRepository.save(cart);
            }
        }
        return false;
    }
}
