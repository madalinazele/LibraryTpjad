package com.library.core.events.listeners;

import java.util.UUID;
import com.library.core.events.RegistrationEvent;
import com.library.core.model.User;
import com.library.core.service.utils.EmailService;
import com.library.core.service.utils.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<RegistrationEvent> {

    private final VerificationTokenService tokenService;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(RegistrationEvent event){
        User user = event.getUser();
        String tokenValue = UUID.randomUUID().toString();

        tokenService.create(user, tokenValue);
        String confirmUrl = constructConfirmUrl(tokenValue);
        sendVerificationMail(confirmUrl, user);
    }

    private String constructConfirmUrl(String tokenValue) {
        String tokenUrl = "/account-verification/?token=" + tokenValue;
        String confirmUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path(tokenUrl).build().toUriString();
        return confirmUrl;
    }

    private void sendVerificationMail(String confirmUrl, User user){
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String message = "Activate your account by accessing the following link: " + "\r\n" + confirmUrl;

        emailService.send(recipientAddress, subject, message);
    }
}
