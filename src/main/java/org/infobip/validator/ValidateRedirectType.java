package org.infobip.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CustomRedirectTypeValidator.class)
public @interface ValidateRedirectType {
    String message() default "Invalid redirect type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
