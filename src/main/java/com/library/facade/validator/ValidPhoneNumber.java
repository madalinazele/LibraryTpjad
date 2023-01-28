package com.library.facade.validator;

import com.library.facade.validator.impl.ValidatorPhoneNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidatorPhoneNumber.class)
@Documented
public @interface ValidPhoneNumber {
    String message() default "The phone number you specified is not valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
