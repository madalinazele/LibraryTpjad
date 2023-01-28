package com.library.web.controller.customer;

import com.library.facade.CartFacade;
import com.library.facade.OrderFacade;
import com.library.facade.UserFacade;
import com.library.facade.dto.CartDto;
import com.library.facade.dto.CheckoutDto;
import com.library.facade.dto.OrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private CartFacade cartFacade;
    private OrderFacade orderFacade;
    private UserFacade userFacade;

    @GetMapping
    public String showCheckoutPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartDto cartDto = cartFacade.getCurrentCart(request, response);
        CheckoutDto checkoutDto = orderFacade.buildCheckoutDto(email, cartDto.getTotalPrice());

        model.addAttribute("checkout", checkoutDto);
        model.addAttribute("cart", cartDto);
        return "customer/checkout";
    }

    @PostMapping("/submit")
    public String checkoutOrder(@ModelAttribute("checkout") CheckoutDto checkoutDto, Model model, HttpServletRequest request, HttpServletResponse response) {
        OrderDto order = orderFacade.checkout(checkoutDto, request, response);

        if (order != null) {
            model.addAttribute("order", order);
            return "customer/checkoutSuccess";
        }
        return "customer/index";
    }
}
