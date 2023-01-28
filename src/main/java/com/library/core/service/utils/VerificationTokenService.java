package com.library.core.service.utils;

import com.library.core.model.User;
import com.library.core.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {

    void create(User user, String token);

    Optional<VerificationToken> get(String VerificationToken);

    void delete(VerificationToken verificationToken);
}
