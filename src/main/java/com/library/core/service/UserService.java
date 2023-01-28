package com.library.core.service;

import com.library.core.model.User;
import java.util.Optional;

public interface UserService {

    boolean save(User user);

    boolean update(User user);

    Optional<User> getById(Integer id);

    Optional<User> register(User user);

    Optional<User> getByEmail(String email);
}
