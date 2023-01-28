package com.library.web.controller.user;
import com.library.core.events.RegistrationEvent;
import com.library.core.model.User;
import com.library.core.exception.UserExistsException;
import com.library.facade.UserFacade;
import com.library.facade.dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Controller
public class AuthController {
    private final ApplicationEventPublisher eventPublisher;
    private final UserFacade userFacade;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) boolean error, Model model) {
        model.addAttribute("error", error);
        return "user/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/addUser";
    }

    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributesModel) {
        if (bindingResult.hasErrors()) {
            return "user/addUser";
        }
        try {
            Optional<User> userOptional = userFacade.register(userDto);
            if (userOptional.isPresent()) {
                User registered = userOptional.get();
                eventPublisher.publishEvent(new RegistrationEvent(registered));
                return "redirect:/";
            }
        } catch (UserExistsException e) {
            redirectAttributesModel.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }
        redirectAttributesModel.addFlashAttribute("errorMessage", "Oops, something is wrong and your account could not be saved!");
        return "redirect:/register";
    }
}
