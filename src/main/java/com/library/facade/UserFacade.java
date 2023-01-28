package com.library.facade;

import com.library.core.model.User;
import com.library.facade.dto.UserDto;

import java.util.Optional;

public interface UserFacade {

    boolean save(UserDto userDto);

    Optional<User> register(UserDto userDto);
}
