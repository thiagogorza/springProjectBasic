package com.thiagodev.springprojectbasic.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;



@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ClienteUpdateValidator.class})
public @interface ClienteUpdate {
    String message() default "Insira um email valido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
