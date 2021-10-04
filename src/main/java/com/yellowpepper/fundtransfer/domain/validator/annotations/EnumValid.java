package com.yellowpepper.fundtransfer.domain.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumValid {

    String message() default "{com.fullstack.EnumValid.message}";

    Class<?>[] groups() default {};

    Class<? extends Enum> enumClass() default Enum.class;

    Class<? extends Payload>[] payload() default {};

    boolean ignoreCase() default false;
}
