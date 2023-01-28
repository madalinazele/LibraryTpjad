package com.library.facade.impl;

import com.library.core.exception.ExpiredTokenException;
import com.library.core.exception.InvalidTokenException;
import com.library.core.model.User;
import com.library.core.model.VerificationToken;
import com.library.core.service.UserService;
import com.library.core.service.utils.VerificationTokenService;
import com.library.facade.AuthenticationFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DefaultAuthenticationFacade implements AuthenticationFacade {
    private final VerificationTokenService tokenService;
    private final UserService userService;

    @Override
    public void activateUser(String tokenValue) {
        Optional<VerificationToken> optionalToken = tokenService.get(tokenValue);
        if (optionalToken.isEmpty()) {
            throw new InvalidTokenException(tokenValue);
        }
        VerificationToken verificationToken = optionalToken.get();
        User user = verificationToken.getUser();
        if (LocalDateTime.now().isAfter(verificationToken.getExpirationTime())) {
            tokenService.delete(verificationToken);
            throw new ExpiredTokenException(tokenValue);
        }
        user.setActive(true);
        userService.update(user);
        tokenService.delete(verificationToken);
    }
}
