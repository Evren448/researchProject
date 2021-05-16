package com.game.webservice.Annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.game.webservice.Validators.UniqueEmailValidator;


@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueEmailValidator.class })

public @interface UniqueEmail {

	String message() default "E-mail must be unique";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}