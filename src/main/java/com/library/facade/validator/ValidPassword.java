package com.library.facade.validator;

import com.library.facade.validator.impl.ValidatorPassword;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidatorPassword.class)
@Documented
public @interface ValidPassword {
    String message() default "Password must be at least 8 characters, and must have at least one uppercase letter, digit and symbol.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
