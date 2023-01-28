package com.library.facade.validator.impl;

import com.library.facade.dto.UserDto;
import com.library.facade.validator.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorPasswordMatches implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {
        UserDto user = (UserDto)target;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
