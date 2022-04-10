package com.thiagodev.springprojectbasic.service.validation;

import com.thiagodev.springprojectbasic.service.validation.ClienteUpdateValidator;
import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;



@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ClienteUpdateValidator.class})
public @interface ClienteUpdate {
    String message() default "Insira um email valido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}