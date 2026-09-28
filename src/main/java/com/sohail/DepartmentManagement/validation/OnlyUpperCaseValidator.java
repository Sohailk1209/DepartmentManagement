package com.sohail.DepartmentManagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyUpperCaseValidator implements ConstraintValidator<OnlyUpperCase, String> {
    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        int n = title.length();

        for(int i = 0 ; i < n ; i++){
            char c = title.charAt(i);

            if(!Character.isLetter(c)){
                return false;
            }
            if('a' <= c && c <= 'z'){
                return false;
            }
        }
        return true;
    }
}
