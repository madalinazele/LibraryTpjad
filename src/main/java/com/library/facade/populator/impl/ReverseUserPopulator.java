package com.library.facade.populator.impl;

import com.library.core.model.Role;
import com.library.core.model.User;
import com.library.facade.dto.UserDto;
import com.library.facade.populator.Populator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class ReverseUserPopulator implements Populator<UserDto, User> {

    @Qualifier("passwordEncoder")
    private final PasswordEncoder passwordEncoder;

    @Override
    public void populate(UserDto userDto, User user) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_CLIENT);
    }
}
