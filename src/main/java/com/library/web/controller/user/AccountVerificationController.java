package com.library.web.controller.user;

import com.library.core.exception.ExpiredTokenException;
import com.library.core.exception.InvalidTokenException;
import com.library.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/account-verification")
public class AccountVerificationController {
    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/")
    public String confirmRegistration(@RequestParam("token") String token, Model model) {
        try {
            authenticationFacade.activateUser(token);
        } catch (InvalidTokenException | ExpiredTokenException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/registerError";
        }
        return "redirect:/login";
    }

}
