package com.library.core.service.utils.impl;

import com.library.core.model.User;
import com.library.core.model.VerificationToken;
import com.library.core.repository.VerificationTokenRepository;
import com.library.core.service.utils.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultVerificationTokenService implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    @Override
    public void create(User user, String value) {
        VerificationToken token = new VerificationToken(value, user);
        token.setExpirationTime(computeExpirationTime(2));
        tokenRepository.save(token);
    }

    @Override
    public Optional<VerificationToken> get(String value) {
        return tokenRepository.findByValue(value);
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        tokenRepository.delete(verificationToken);
    }

    private LocalDateTime computeExpirationTime(int lifeTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusHours(lifeTime);

        return expirationTime;
    }
}
