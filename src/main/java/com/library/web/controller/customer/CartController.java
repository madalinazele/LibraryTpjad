package com.library.web.controller.customer;

import com.library.facade.CartFacade;
import com.library.facade.dto.CartDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private CartFacade cartFacade;

    @PostMapping("/add/{productId}")
    public String addProductToCart(@PathVariable Integer productId, HttpServletRequest request, HttpServletResponse response) {
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        cartFacade.addToCart(request, response, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/delete/{cartEntryId}")
    public String delete(@PathVariable Integer cartEntryId, HttpServletRequest request, HttpServletResponse response) {
        cartFacade.delete(request, response, cartEntryId);
        return "redirect:/cart";
    }

    @PostMapping("/update/{cartEntryId}")
    public String updateQuantity(@PathVariable Integer cartEntryId, HttpServletRequest request, HttpServletResponse response) {
        cartFacade.updateCartEntry(cartEntryId, Integer.parseInt(request.getParameter("quantity")), request, response);
        return "redirect:/cart";
    }

    @GetMapping
    public String getProductsCart(Model model,  HttpServletRequest request, HttpServletResponse response) {
        CartDto cartDto = cartFacade.getCurrentCart(request, response);
        model.addAttribute("cartDto", cartDto);
        return "customer/cartProducts";
    }
}

