package com.library.facade;


import com.library.facade.dto.CartDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartFacade {

    CartDto getCurrentCart(HttpServletRequest request, HttpServletResponse response);

    boolean delete(HttpServletRequest request, HttpServletResponse response, Integer cartEntryId);

    boolean addToCart(HttpServletRequest request, HttpServletResponse response, Integer productId, Integer quantity);

    boolean updateCartEntry(Integer cartEntryId, Integer quantity, HttpServletRequest request, HttpServletResponse response);

    void assignCartToCurrentUser(HttpServletRequest request, HttpServletResponse response);
}
