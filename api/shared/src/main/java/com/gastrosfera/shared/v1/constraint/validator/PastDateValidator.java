package com.gastrosfera.shared.v1.constraint.validator;


import com.gastrosfera.shared.v1.constraint.PastDateConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;

public class PastDateValidator implements ConstraintValidator<PastDateConstraint, Instant> {

    @Override
    public void initialize(PastDateConstraint contactNumber) {
    }

    @Override
    public boolean isValid(Instant instant, ConstraintValidatorContext constraintValidatorContext) {
        return instant == null || instant.isBefore(Instant.now());
    }
}
