package com.library.facade.impl;

import com.library.core.exception.UserExistsException;
import com.library.core.model.User;
import com.library.core.service.UserService;
import com.library.facade.UserFacade;
import com.library.facade.converter.Converter;
import com.library.facade.dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class DefaultUserFacade implements UserFacade {

    private final UserService userService;
    @Qualifier("defaultUserConverter")
    private final Converter<User, UserDto> defaultUserConverter;
    @Qualifier("reversedUserConverter")
    private final Converter<UserDto, User> reversedUserConverter;

    @Override
    public Optional<User> register(UserDto userDto) throws UserExistsException {
        final User user = getReversedUserConverter().convert(userDto);
        return userService.register(user);
    }

    @Override
    public boolean save(UserDto userDto) throws UserExistsException {
        final User user = getReversedUserConverter().convert(userDto);
        return userService.save(user);
    }
}
