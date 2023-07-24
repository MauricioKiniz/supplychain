package com.mksistemas.supplychain.library.validators.tsid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TsidIdValidator.class })
@Documented
public @interface TsidId {
	String message() default "{TsidId.invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
