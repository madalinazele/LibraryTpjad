package com.library.facade.impl;

import com.library.core.model.Cart;
import com.library.core.model.User;
import com.library.core.service.CartService;
import com.library.core.service.UserService;
import com.library.facade.CartFacade;
import com.library.facade.constants.CartConstants;
import com.library.facade.converter.Converter;
import com.library.facade.dto.CartDto;
import com.library.facade.utils.CookieUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class DefaultCartFacade implements CartFacade {

    private final CartService cartService;
    private final UserService userService;

    @Qualifier("cartDtoFullConverter")
    private final Converter<Cart, CartDto> cartDtoFullConverter;

    private Cart getOrCreateCart(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> cookieOp = CookieUtils.getCookie(CartConstants.COOKIE_NAME, request);

        Optional<Cart> cartOp;
        if (cookieOp.isEmpty()) {
            cartOp = createNewCart(request, response);
        } else {
            String currentCartId = cookieOp.get();
            cartOp = cartService.getById(Integer.valueOf(currentCartId));

            if (cartOp.isEmpty()) {
                cartOp = createNewCart(request, response);
            }
        }
        return cartOp.orElse(null);
    }

    @Override
    public void assignCartToCurrentUser(HttpServletRequest request, HttpServletResponse response) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional = userService.getByEmail(email);

        if (userOptional.isEmpty()) {
            return;
        }

        User user = userOptional.get();
        if (user.getCart() == null) {
            Cart cart = getOrCreateCart(request, response);
            user.setCart(cart);
            cart.setUser(user);
            userService.update(user);
            cartService.update(cart);
        } else {
            CookieUtils.setCookie(CartConstants.COOKIE_NAME, user.getCart().getId().toString(), request.getContextPath(), response);
        }
    }

    @Override
    @Transactional
    public CartDto getCurrentCart(HttpServletRequest request, HttpServletResponse response) {
        Cart cart = getOrCreateCart(request, response);
        return cartDtoFullConverter.convert(cart);
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response, Integer cartEntryId) {
        return cartService.deleteFromCart(cartEntryId);
    }

    @Override
    @Transactional
    public boolean addToCart(HttpServletRequest request, HttpServletResponse response, Integer productId, Integer quantity) {
        Cart cart = getOrCreateCart(request, response);
        return cartService.addToCart(cart, productId, quantity);
    }

    @Override
    @Transactional
    public boolean updateCartEntry(Integer cartEntryId, Integer quantity, HttpServletRequest request, HttpServletResponse response) {
        Cart cart = getOrCreateCart(request, response);
        return cartService.updateCartEntry(cart, cartEntryId, quantity);
    }

    private Optional<Cart> createNewCart(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cart> optionalCart = cartService.save(new Cart());
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            CookieUtils.setCookie(CartConstants.COOKIE_NAME, cart.getId().toString(), request.getContextPath(), response);
        }
        return optionalCart;
    }
}
