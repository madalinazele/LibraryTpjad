package com.library.core.repository;

import com.library.core.model.User;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Integer> {

    Optional<User> register(User user);

    Optional<User> findByEmail(String email);
}
