package com.library.facade.impl;

import com.library.core.model.*;
import com.library.core.service.CartService;
import com.library.core.service.OrderService;
import com.library.core.service.UserService;
import com.library.facade.CartFacade;
import com.library.facade.OrderFacade;
import com.library.facade.converter.Converter;
import com.library.facade.dto.CheckoutDto;
import com.library.facade.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class DefaultOrderFacade implements OrderFacade {
    private UserService userService;
    private OrderService orderService;
    private CartService cartService;
    private CartFacade cartFacade;

    @Qualifier("reverseCheckoutConverter")
    private final Converter<CheckoutDto, Order> reverseCheckoutConverter;

    @Qualifier("orderEntryConverter")
    private final Converter<CartEntry, OrderEntry> orderEntryConverter;

    @Qualifier("orderDtoConverter")
    private final Converter<Order, OrderDto> orderDtoConverter;

    @Override
    @Transactional
    public CheckoutDto buildCheckoutDto(String email, Double totalPrice) {
        User user = userService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));

        CheckoutDto checkoutDto = new CheckoutDto();
        checkoutDto.setName(user.getName());
        checkoutDto.setAddress(user.getAddress());
        checkoutDto.setPhoneNo(user.getPhoneNo());
        checkoutDto.setTotalPrice(totalPrice);

        return checkoutDto;
    }

    @Override
    @Transactional
    public OrderDto checkout(CheckoutDto checkoutDto, HttpServletRequest request, HttpServletResponse response) {
        Order order = assignOrderEntriesToOrder(checkoutDto);

        Optional<Order> savedOrder = orderService.checkout(order);
        if (savedOrder.isPresent()) {
            resetCart(request, response);
            return orderDtoConverter.convert(savedOrder.get());
        }
        return null;
    }

    private void resetCart(HttpServletRequest request, HttpServletResponse response) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        Integer cartId = user.getCart().getId();
        user.setCart(null);
        userService.update(user);

        cartService.delete(cartId);

        cartFacade.assignCartToCurrentUser(request, response);
    }

    private Order assignOrderEntriesToOrder(CheckoutDto checkoutDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
        Cart cart = user.getCart();

        Order order = reverseCheckoutConverter.convert(checkoutDto);
        List<OrderEntry> orderEntryList = cart.getCartEntries()
                .stream()
                .map(orderEntryConverter::convert)
                .peek(orderEntry -> orderEntry.setOrder(order))
                .collect(Collectors.toList());

        order.setOrderEntries(orderEntryList);

        return order;
    }
}
