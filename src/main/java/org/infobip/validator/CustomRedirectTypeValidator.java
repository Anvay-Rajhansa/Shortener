package org.infobip.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CustomRedirectTypeValidator implements ConstraintValidator<ValidateRedirectType, Integer> {

    private List<Integer> valueList;

    @Override
    public void initialize(ValidateRedirectType validRedirectType) {
        valueList = Arrays.asList(302, 301);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(integer);
    }
}
