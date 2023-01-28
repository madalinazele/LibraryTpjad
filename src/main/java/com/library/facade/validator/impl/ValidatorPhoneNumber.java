package com.library.facade.validator.impl;

import com.library.facade.validator.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidatorPhoneNumber implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final Pattern pattern = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$");

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(phoneNo).matches();
    }

}
