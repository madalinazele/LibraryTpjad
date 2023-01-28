package com.library.facade.validator;

import com.library.facade.validator.impl.ValidatorEmail;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidatorEmail.class)
@Documented
public @interface ValidEmail {
    String message() default "Specified email is not valid!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
