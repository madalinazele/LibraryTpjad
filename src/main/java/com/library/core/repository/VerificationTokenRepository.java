package com.library.core.repository;

import com.library.core.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends AbstractRepository<VerificationToken, Integer> {

    Optional<VerificationToken> findByValue(String value);
}
