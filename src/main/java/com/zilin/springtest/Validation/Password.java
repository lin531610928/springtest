package com.zilin.springtest.Validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.regex.Pattern;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = Password.PasswordChecker.class)
public @interface Password {
    String message() default "Password format is incorrect";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PasswordChecker implements ConstraintValidator<Password, String>{

        @Override
        public void initialize(Password constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            if(s == null){
                return false;
            }
            String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{8,16}$";
            if(Pattern.matches(pattern, s) == true){
                return true;
            }
            return false;
        }
    }
}
