package com.thiagodev.springprojectbasic.services.validation;


import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ClienteInsertValidator.class})
public @interface ClienteInsert {
    String message() default "Passed value is not valid as a CPF or CNPJ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
