package com.library.facade.populator.impl;

import com.library.core.model.User;
import com.library.facade.dto.UserDto;
import com.library.facade.populator.Populator;

public class DefaultUserPopulator implements Populator<User, UserDto> {

    @Override
    public void populate(User user, UserDto userDto) {
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNo(user.getPhoneNo());
        userDto.setPassword(user.getPassword());
    }

}
