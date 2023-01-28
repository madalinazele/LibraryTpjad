package com.library.core.service.impl;

import com.library.core.exception.UserExistsException;
import com.library.core.model.User;
import com.library.core.repository.UserRepository;
import com.library.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private static final String USER_NOT_FOUND = "User with id {} was not found!";

    private final UserRepository userRepository;

    @Override
    public boolean save(User user) throws UserExistsException {
        Optional<User> matchingUser = getByEmail(user.getEmail());
        if (matchingUser.isPresent()) {
            throw new UserExistsException("An account with email address \"" + user.getEmail() + "\" already exists!");
        }
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        if (!userRepository.update(user)) {
            log.error(USER_NOT_FOUND, user.getId());
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> getById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        }
        log.error(USER_NOT_FOUND, id);
        return Optional.empty();
    }

    @Override
    public Optional<User> register(User user) throws UserExistsException {
        Optional<User> matchingUser = getByEmail(user.getEmail());
        if (matchingUser.isPresent()) {
            throw new UserExistsException("An account with email address \"" + user.getEmail() + "\" already exists!");
        }
        return userRepository.register(user);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
